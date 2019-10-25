package com.jarqprog.artGallery.api.dataLogic.useCases;


import com.jarqprog.artGallery.domain.dto.fatDTO.RoleFat;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleFat findByRole(@NonNull AuthorizationRole role);

}
