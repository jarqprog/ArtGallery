package com.jarqprog.personapi.read.readuser;

import com.jarqprog.commonapi.exceptions.ResourceNotFoundException;
import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.roleuser.RoleUser;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.domainperson.model.user.User;
import com.jarqprog.domainperson.usecase.login.UserLoginDTO;
import com.jarqprog.personapi.domains.roleUser.RoleUserRepository;
import com.jarqprog.personapi.domains.user.UserEntity;
import com.jarqprog.personapi.domains.user.UserRepository;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readuser.DomainReadUser;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ReadUserServiceImpl implements ReadUserService {

    private static final Logger logger = LoggerFactory.getLogger(ReadUserServiceImpl.class);

    @NonNull private final UserRepository userRepository;
    @NonNull private final RoleUserRepository roleUserRepository;
    @NonNull private final ReadUserDTOConverter readUserDTOConverter;

    @Autowired
    public ReadUserServiceImpl(@NonNull UserRepository userRepository,
                               @NonNull RoleUserRepository roleUserRepository,
                               @NonNull ReadUserDTOConverter readUserDTOConverter) {
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
        this.readUserDTOConverter = readUserDTOConverter;
    }

    @Override
    public ApiReadUserDTO getReadUserByLogin(@NonNull UserLoginDTO userLoginDTO) {
        logger.info("Try to find user by UserLogin: {}", userLoginDTO);
        final var user = retrieveByUserLogin(userLoginDTO);
        logger.info("Got User: {}", user);
        final Set<SystemRole> roles = retrieveRolesByUserLogin(userLoginDTO);
        logger.info("Got roles: {}", roles);
        final var readUser = DomainReadUser.createWith()
                .user(user)
                .contact(user.getContact())
                .roles(roles)
                .build();
        logger.info("ReadModel: {}", readUser);
        return readUserDTOConverter.mapToDTO(readUser);
    }

    private User retrieveByUserLogin(final UserLoginDTO userLoginDTO) {
        return userRepository.findUserByLoginAndPassword(userLoginDTO.login(), userLoginDTO.password())
                .map(DomainUser::fromUser)
                .orElseThrow(() -> new ResourceNotFoundException(UserEntity.class));
    }

    private Set<SystemRole> retrieveRolesByUserLogin(final UserLoginDTO userLoginDTO) {
        return roleUserRepository.findAllByUserEntityLogin(userLoginDTO.login())
                .stream()
                .map(RoleUser::getRole)
                .collect(Collectors.toSet());
    }
}
