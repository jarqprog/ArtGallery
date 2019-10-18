package com.jarqprog.artGallery.springData.services;

import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.Role;
import com.jarqprog.artGallery.domain.entity.Roles;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.components.DtoEntityConverter;
import com.jarqprog.artGallery.domain.useCases.RoleService;
import com.jarqprog.artGallery.springData.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleRoleService implements RoleService {

    private final RoleRepository roleRepository;
    private final DtoEntityConverter dtoEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(SimpleRoleService.class);

    @Autowired
    public SimpleRoleService(RoleRepository roleRepository, DtoEntityConverter dtoEntityConverter) {
        this.roleRepository = roleRepository;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    @Override
    public RoleDTO findByRole(Roles role) {
        Role founded = roleRepository.findByRole(role)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class));
        return dtoEntityConverter.convertEntityToDto(founded, RoleDTO.class);
    }
}
