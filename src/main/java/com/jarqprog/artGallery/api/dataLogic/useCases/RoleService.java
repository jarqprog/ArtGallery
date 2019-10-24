package com.jarqprog.artGallery.api.dataLogic.useCases;


import com.jarqprog.artGallery.domain.dto.heavyDto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleDTO findByRole(@NonNull Roles role);

}
