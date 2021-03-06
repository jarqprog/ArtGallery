package com.jarqprog.personapi.user;

import com.jarqprog.commonapi.components.DtoConverter;
import com.jarqprog.commonapi.exceptions.ResourceAlreadyExists;
import com.jarqprog.commonapi.exceptions.ResourceNotFound;
import com.jarqprog.domainperson.SystemRole;
import com.jarqprog.domainperson.user.DomainUser;
import com.jarqprog.domainperson.user.User;
import com.jarqprog.domainperson.user.UserData;
import com.jarqprog.domainperson.roleuser.DomainRoleUser;
import com.jarqprog.domainperson.roleuser.RoleUser;
import com.jarqprog.personapi.contact.ContactEntity;
import com.jarqprog.personapi.contact.ContactRepository;
import com.jarqprog.personapi.roleUser.RoleUserEntity;
import com.jarqprog.personapi.roleUser.RoleUserRepository;
import com.jarqprog.personapi.user.dto.ApiUserDTO;
import com.jarqprog.personapi.user.dto.UserFat;
import com.jarqprog.personapi.user.dto.UserThin;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @NonNull private final UserRepository userRepository;
    @NonNull private final ContactRepository contactRepository;
    @NonNull private final RoleUserRepository roleUserRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final PasswordEncoder passwordEncoder;
    @NonNull private final PasswordValidator passwordValidator;

    @Autowired
    public UserServiceImpl(@NonNull UserRepository userRepository,
                           @NonNull ContactRepository contactRepository,
                           @NonNull RoleUserRepository roleUserRepository,
                           @NonNull DtoConverter dtoConverter,
                           @NonNull PasswordEncoder passwordEncoder,
                           @NonNull PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.roleUserRepository = roleUserRepository;
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
        this.passwordValidator = passwordValidator;
    }



    @Override
    public List<ApiUserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoConverter.transformEntityTo(p, UserThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <U extends ApiUserDTO> List<U> getAllUsers(Class<U> clazz) {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoConverter.transformEntityTo(p, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public ApiUserDTO findUserById(long id) {
        UserEntity userEntity = findById(id);
        return dtoConverter.transformEntityTo(userEntity, UserFat.class);
    }

    @Override
    public <U extends ApiUserDTO> U findUserById(long id, Class<U> clazz) {
        UserEntity userEntity = findById(id);
        return dtoConverter.transformEntityTo(userEntity, clazz);
    }

    @Override
    public ApiUserDTO findUserByLogin(String login) {
        UserEntity userEntity = findByLogin(login);
        logger.info("Found User {} by login {}", userEntity, login);
        return dtoConverter.transformEntityTo(userEntity, UserFat.class);
    }

    @Override
    public <U extends ApiUserDTO> U findUserByLogin(String login, Class<U> clazz) {
        UserEntity userEntity = findByLogin(login);
        logger.info("Found User {} by login {}", userEntity, login);
        return dtoConverter.transformEntityTo(userEntity, clazz);
    }

    @Override
    public long addUser(@NonNull final UserData userData) {
        final SystemRole defaultRole = SystemRole.USER;
        return createUserWithRole(userData, defaultRole);
    }

    @Override
    @Transactional
    public long createUserWithRole(@NonNull final UserData userData, @NonNull final SystemRole role) {
        preventCreatingExistingUser(userData.getId());
        // validation
        validateContactExists(userData.getContactId());

        final User user = createUser(userData);
        final UserEntity userEntity = userRepository.save(UserEntity.fromUser(user));

        final String login = userEntity.getLogin();
        if (roleUserRepository.findByRoleAndUserEntityLogin(role, login).isPresent()) {
            logger.warn("On User creation: Role-user for login {} already exists - deleting this data", login);
            roleUserRepository.deleteAllByUserEntityLogin(login);
        }

        RoleUser roleUser = DomainRoleUser.createWith()
                .role(role)
                .user(userEntity)
                .build();
        roleUserRepository.save(RoleUserEntity.fromRoleUser(roleUser));
        logger.info("Role {} created for User with login={} and ID={}", role, userEntity.getLogin(), userEntity.getId());

        logger.info("User created with login={} and ID={}", userEntity.getLogin(), userEntity.getId());
        return userEntity.getId();
    }

    @Override
    @Transactional
    public void updateUser(long id, @NonNull UserData userData) {
        if (id != userData.getId()) {
            throw new IllegalArgumentException("Invalid user ID numbers were provided");
        }
        validateUserExists(id);
        // validation
        validateContactExists(userData.getContactId());

        UserEntity userEntity = findById(id);
        updateUserEntity(userEntity, userData);
        userRepository.save(userEntity);
        logger.info("User updated with login={} and ID={}", userEntity.getLogin(), userEntity.getId());
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        UserEntity userEntity = findById(id);
        roleUserRepository.deleteAllByUserEntityLogin(userEntity.getLogin());
        //todo run query on picture, commentary DB to set login to 'anonymous'
        userRepository.deleteById(id);
        logger.info("removing user with ID={} and login={}", id, userEntity.getLogin());
    }

    private UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFound(UserEntity.class, id));
    }

    private ContactEntity findContactById(Long contactId) {
        return contactRepository
                .findById(contactId).orElseThrow(() -> new ResourceNotFound(ContactEntity.class, contactId));
    }

    private UserEntity findByLogin(String login) {
        logger.info("Trying to find User by login {}", login);
        return userRepository.findUserByLogin(login).orElseThrow(() -> new ResourceNotFound(UserEntity.class));
    }

    private void validateUserExists(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFound(UserEntity.class, userId);
        }
    }

    private void validateContactExists(long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new ResourceNotFound(ContactEntity.class, contactId);
        }
    }

    private void preventCreatingExistingUser(long userId) {
        if (userRepository.existsById(userId)) {
            throw new ResourceAlreadyExists(UserEntity.class, userId);
        }
    }

    // temporary
    private User createUser(final UserData userData) {
        long contactId = userData.getContactId();
        //todo
        String password = userData.getPassword();
        if (StringUtils.isBlank(password)) { // for existing User it should be done using different path
            logger.warn("Cannot assign password to User with login {}. Password not exists", userData.getLogin());
            password = "default";
        }
        final String encoded = passwordEncoder.encode(password);
        return DomainUser
                .createWith()
                .login(userData.getLogin())
                .contact(findContactById(userData.getContactId()))
                .password(encoded)
                .build();
    }

    private void updateUserEntity(final UserEntity userEntity, UserData userData) {
        logger.info("Updating user {} - nothing to update", userEntity.getId());
        // do nothing
    }
}
