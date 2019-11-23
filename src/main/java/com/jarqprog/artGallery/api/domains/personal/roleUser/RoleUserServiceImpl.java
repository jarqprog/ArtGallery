package com.jarqprog.artGallery.api.domains.personal.roleUser;

import com.jarqprog.artGallery.api.domains.personal.roleUser.dto.RoleUserDTO;
import com.jarqprog.artGallery.api.domains.personal.roleUser.dto.RoleUserThin;
import com.jarqprog.artGallery.api.domains.personal.user.UserEntity;
import com.jarqprog.artGallery.api.domains.personal.user.UserRepository;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;

import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.personal.*;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleUserServiceImpl implements RoleUserService {

    @NonNull private final RoleUserRepository roleUserRepository;
    @NonNull private final UserRepository userRepository;
    @NonNull private final DtoConverter dtoConverter;

    private static final Logger logger = LoggerFactory.getLogger(RoleUserServiceImpl.class);

    @Autowired
    public RoleUserServiceImpl(@NonNull RoleUserRepository roleUserRepository,
                               @NonNull UserRepository userRepository,
                               @NonNull DtoConverter dtoConverter) {
        this.roleUserRepository = roleUserRepository;
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public Set<RoleUserDTO> findByUserLogin(String userLogin) {
        Set<RoleUserEntity> userRoles = roleUserRepository.findAllByUserEntityLogin(userLogin);
        return userRoles
                .stream()
                .map(r -> dtoConverter.transformEntityTo(r, RoleUserThin.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleUserDTO addUserRole(@NonNull SystemRole role, @NonNull UserData userData) {
        RoleUserEntity roleUserEntity = roleUserRepository.findByRoleAndUserEntityLogin(role, userData.getLogin()).orElse(null);
        if (roleUserEntity != null) {
            throw new IllegalArgumentException(String.format("Role: %s for user login: %s already exists!",
                    role, userData.getLogin()));
        }

        UserEntity userEntity = userRepository.findUserByLogin(userData.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException(UserEntity.class, userData.getId()));

        RoleUser roleUser = DomainRoleUser.createWith()
                .role(role)
                .user(userEntity)
                .build();

        RoleUserEntity saved = roleUserRepository.save(RoleUserEntity.fromRoleUser(roleUser));
        logger.info("Created role={} for user={}", role, userData.getLogin());
        return dtoConverter.transformEntityTo(saved, RoleUserThin.class);
    }
}