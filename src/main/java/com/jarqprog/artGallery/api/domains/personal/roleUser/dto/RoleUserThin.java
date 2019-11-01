package com.jarqprog.artGallery.api.domains.personal.roleUser.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import lombok.*;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class RoleUserThin extends DomainDTO implements RoleUserDTO {

    private SystemRole role;
    private String userLogin;

}
