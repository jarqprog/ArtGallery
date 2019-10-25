package com.jarqprog.artGallery.api.dataLogic.useCases.impl;

import com.jarqprog.artGallery.domain.dto.fatDTO.RoleFat;
import com.jarqprog.artGallery.domain.entity.Role;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.useCases.RoleService;
import com.jarqprog.artGallery.api.dataLogic.repositories.RoleRepository;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SimpleRoleService implements RoleService {

    @NonNull private final RoleRepository roleRepository;
    @NonNull private final DtoConverter dtoConverter;

    private static final Logger logger = LoggerFactory.getLogger(SimpleRoleService.class);

    @Autowired
    public SimpleRoleService(@NonNull RoleRepository roleRepository,
                             @NonNull DtoConverter dtoConverter) {
        this.roleRepository = roleRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public RoleFat findByRole(AuthorizationRole role) {
        Role founded = roleRepository.findByRole(role)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class));
        return dtoConverter.convertEntityToDTO(founded, RoleFat.class);
    }
}
