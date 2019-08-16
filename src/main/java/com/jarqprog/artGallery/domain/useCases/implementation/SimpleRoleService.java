package com.jarqprog.artGallery.domain.useCases.implementation;

import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.Role;
import com.jarqprog.artGallery.domain.entity.Roles;
import com.jarqprog.artGallery.domain.exception.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.domain.useCases.RoleService;
import com.jarqprog.artGallery.springData.repository.RoleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleRoleService implements RoleService {

    @Autowired private RoleRepository roleRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;

    private static final Logger logger = Logger.getLogger(SimpleRoleService.class);

    @Override
    public RoleDTO findByRole(Roles role) {
        Role founded = roleRepository.findByRole(role)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class));
        return dtoEntityConverter.convertEntityToDto(founded, RoleDTO.class);
    }
}
