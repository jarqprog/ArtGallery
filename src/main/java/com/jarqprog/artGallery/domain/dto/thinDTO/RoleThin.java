package com.jarqprog.artGallery.domain.dto.thinDTO;

import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleThin extends DomainDTO implements RoleDTO, DTOThin {

    private AuthorizationRole role;

}
