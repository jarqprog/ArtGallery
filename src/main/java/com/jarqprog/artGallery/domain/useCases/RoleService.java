package com.jarqprog.artGallery.domain.useCases;


import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleDTO findByRole(Roles role);

}
