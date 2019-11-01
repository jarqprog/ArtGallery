package com.jarqprog.artGallery.api.domains.personal.roleUser.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserFat;

import com.jarqprog.artGallery.domain.personal.RoleUser;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import lombok.*;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class RoleUserFat extends DomainDTO implements RoleUserDTO, RoleUser {

    private SystemRole role;
    private UserFat user;

    @Override
    public String getUserLogin() {
        return user.getLogin();
    }
}
