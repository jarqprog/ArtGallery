package com.jarqprog.artGallery.springData.useCases.impl;

import com.jarqprog.artGallery.domain.dto.heavyDto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.Role;
import com.jarqprog.artGallery.domain.entity.Roles;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.springData.useCases.RoleService;
import com.jarqprog.artGallery.springData.repository.RoleRepository;

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
    public RoleDTO findByRole(Roles role) {
        Role founded = roleRepository.findByRole(role)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class));
        return dtoConverter.convertEntityToDto(founded, RoleDTO.class);
    }
}
