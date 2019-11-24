package com.jarqprog.personapi.read;

import com.jarqprog.commonapi.components.DtoConverter;
import com.jarqprog.commonapi.exceptions.ResourceNotFoundException;
import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.roleuser.RoleUser;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.domainperson.usecase.login.UserLogin;
import com.jarqprog.personapi.domains.contact.ContactRepository;
import com.jarqprog.personapi.domains.roleUser.RoleUserRepository;
import com.jarqprog.personapi.domains.user.UserEntity;
import com.jarqprog.personapi.domains.user.UserRepository;
import com.jarqprog.personapi.domains.user.validation.passwordValidation.PasswordValidator;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import readuser.DomainReadUser;
import readuser.ReadUser;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ReadUserServiceImpl implements ReadUserService {

    private static final Logger logger = LoggerFactory.getLogger(ReadUserServiceImpl.class);

    @NonNull private final UserRepository userRepository;
    @NonNull private final ContactRepository contactRepository;
    @NonNull private final RoleUserRepository roleUserRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final PasswordEncoder passwordEncoder;
    @NonNull private final PasswordValidator passwordValidator;

    @Autowired
    public ReadUserServiceImpl(@NonNull UserRepository userRepository,
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
    public ReadUser getReadUserByLogin(@NonNull UserLogin userLogin) {
        final var login = userLogin.login();
        final var user = userRepository.findUserByLoginAndPassword(login, userLogin.password())
                .map(DomainUser::fromUser)
                .orElseThrow(() -> new ResourceNotFoundException(UserEntity.class));
        final Set<SystemRole> roles = roleUserRepository.findAllByUserEntityLogin(login)
                .stream()
                .map(RoleUser::getRole)
                .collect(Collectors.toSet());
        return DomainReadUser.buildWithData(user, roles);
    }
}
