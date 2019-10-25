package com.jarqprog.artGallery.domain.dto.fatDTO;

import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleFat extends DomainDTO implements RoleDTO, DTOFat {

    private AuthorizationRole role;
}
