package com.jarqprog.artGallery.api.domains.personal.roleUser.dto;

import com.jarqprog.artGallery.api.domains.DTOFat;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserFat;

import com.jarqprog.artGallery.domain.personal.SystemRole;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper=true)
public class RoleUserFat extends AbstractRoleUserDTO implements DTOFat {

    @NonNull
    @NotNull
    private UserFat user;

    public RoleUserFat(@NonNull SystemRole role, @NonNull UserFat user) {
        super(role);
        this.user = user;
    }

    @Override
    public String getUserLogin() {
        return user.getLogin();
    }
}
