package com.jarqprog.artGallery.api.domains.personal.roleUser;

import com.jarqprog.artGallery.api.domains.personal.user.UserEntity;
import com.jarqprog.artGallery.api.domains.personal.user.UserRepository;
import com.jarqprog.artGallery.api.domains.personal.roleUser.dto.RoleUserFat;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;

import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.User;
import com.jarqprog.artGallery.domain.personal.RoleUser;
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
    public Set<RoleUser> findByUserLogin(String userLogin) {
        Set<RoleUserEntity> userRoles = roleUserRepository.findAllByUserLogin(userLogin);
        return userRoles
                .stream()
                .map(r -> dtoConverter.convertEntityToModel(r, RoleUserFat.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleUser addUserRole(@NonNull SystemRole role, @NonNull User user) {
        RoleUserEntity roleUserEntity = roleUserRepository.findByRoleAndUserLogin(role, user.getLogin()).orElse(null);
        if (roleUserEntity != null) {
            throw new IllegalArgumentException(String.format("Role: %s for user login: %s already exists!",
                    role, user.getLogin()));
        }
        UserEntity userEntity = userRepository.findUserByLogin(user.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException(UserEntity.class, user.getId()));
        RoleUserEntity saved = roleUserRepository.save(new RoleUserEntity(role, userEntity));
        return dtoConverter.convertEntityToModel(saved, RoleUserFat.class);
    }
}