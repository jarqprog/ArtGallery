package com.jarqprog.artGallery.api.domains.personal.user;

import com.jarqprog.artGallery.api.domains.personal.contact.ContactRepository;
import com.jarqprog.artGallery.api.domains.personal.user.validation.passwordValidation.PasswordValidator;
import com.jarqprog.artGallery.api.domains.personal.roleUser.RoleUserEntity;
import com.jarqprog.artGallery.api.domains.personal.roleUser.RoleUserRepository;
import com.jarqprog.artGallery.domain.personal.*;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserThin;
import com.jarqprog.artGallery.api.domains.personal.contact.ContactEntity;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserFat;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;
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
    public List<UserData> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoConverter.transformEntityTo(p, UserThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <U extends UserData> List<U> getAllUsers(Class<U> clazz) {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoConverter.transformEntityTo(p, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public UserData findUserById(long id) {
        UserEntity userEntity = findById(id);
        return dtoConverter.transformEntityTo(userEntity, UserFat.class);
    }

    @Override
    public <U extends UserData> U findUserById(long id, Class<U> clazz) {
        UserEntity userEntity = findById(id);
        return dtoConverter.transformEntityTo(userEntity, clazz);
    }

    @Override
    public UserData findUserByLogin(String login) {
        UserEntity userEntity = findByLogin(login);
        return dtoConverter.transformEntityTo(userEntity, UserFat.class);
    }

    @Override
    public <U extends UserData> U findUserByLogin(String login, Class<U> clazz) {
        UserEntity userEntity = findByLogin(login);
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
        validateUserExists(id);
        // validation

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
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserEntity.class, id));
    }

    private ContactEntity findContactById(Long contactId) {
        return contactRepository
                .findById(contactId).orElseThrow(() -> new ResourceNotFoundException(ContactEntity.class, contactId));
    }

    private UserEntity findByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() -> new ResourceNotFoundException(UserEntity.class));
    }

    private void validateUserExists(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(UserEntity.class, userId);
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
                .contact(userData.hasContact() ? findContactById(userData.getContactId()) : null)
                .password(encoded)
                .build();
    }

    private void updateUserEntity(final UserEntity userEntity, UserData userData) {
        logger.info("Updating user {} - nothing to update", userEntity.getId());
        // do nothing
    }
}
