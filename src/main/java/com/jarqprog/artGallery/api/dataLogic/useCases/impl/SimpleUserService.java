package com.jarqprog.artGallery.api.dataLogic.useCases.impl;

import com.jarqprog.artGallery.api.dataLogic.repositories.*;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.thinDTO.UserThin;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import com.jarqprog.artGallery.domain.entity.Contact;
import com.jarqprog.artGallery.domain.entity.Role;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.domain.dto.fatDTO.UserFat;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.useCases.UserService;
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
@Transactional
public class SimpleUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCommentaryService.class);

    @NonNull private final UserRepository userRepository;
    @NonNull private final ContactRepository contactRepository;
    @NonNull private final RoleRepository roleRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final PasswordEncoder passwordEncoder;

    @Autowired
    public SimpleUserService(@NonNull UserRepository userRepository,
                             @NonNull ContactRepository contactRepository,
                             @NonNull RoleRepository roleRepository,
                             @NonNull DtoConverter dtoConverter,
                             @NonNull PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.roleRepository = roleRepository;
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToDTO(p, UserThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(long id) {
        User User = findById(id);
        return dtoConverter.convertEntityToDTO(User, UserFat.class);
    }

    @Override
    public UserFat findUserByLogin(String login) {
        User User = findByLogin(login);
        return dtoConverter.convertEntityToDTO(User, UserFat.class);
    }

    @Override
    public UserDTO addUser(@NonNull UserDTO userDTO) {
        preventCreatingExistingUser(userDTO.getId());
        // validation

        User user = new User();
        updateUserByDTO(user, userDTO);
        User saved = userRepository.save(user);
        return dtoConverter.convertEntityToDTO(saved, UserFat.class);
    }

    @Override
    public UserDTO updateUser(long id, @NonNull UserDTO userDTO) {
        validateUserExists(id);
        // validation

        User user = findById(id);
        updateUserByDTO(user, userDTO);
        User saved = userRepository.save(user);
        return dtoConverter.convertEntityToDTO(saved, UserFat.class);
    }

    @Override
    public void removeUser(long id) {
        validateUserExists(id);
        userRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, id));
    }

    private Contact findContactById(Long contactId) {
        return contactRepository
                .findById(contactId).orElseThrow(() -> new ResourceNotFoundException(Contact.class, contactId));
    }

    private User findByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() -> new ResourceNotFoundException(User.class));
    }

    private void validateUserExists(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(User.class, userId);
        }
    }

    private void preventCreatingExistingUser(long userId) {
        if (userRepository.existsById(userId)) {
            throw new ResourceAlreadyExists(User.class, userId);
        }
    }

    private void updateUserByDTO(User user, UserDTO userDTO) {

        long contactId = userDTO.getContactId();
        if (contactId > 0) {
           user.setContact(findContactById(contactId));
        }

        user.setVersion(userDTO.getVersion());
        user.setLogin(userDTO.getLogin());

        if (userDTO.getId() <= 0) { // for existing User it should be done using different path
            user.addRole(roleRepository.findByRole(AuthorizationRole.USER)
                    .orElseThrow(() -> new ResourceNotFoundException(Role.class)));
            String password = userDTO.getPassword();
            if (StringUtils.isNotBlank(password)) {
                user.setPassword(passwordEncoder.encode(password));
            } else {
                logger.warn("Password for new user is blank");
            }
        }

    }
}
