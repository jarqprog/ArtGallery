package com.jarqprog.artGallery.api.domains.personal.roleUser.dto;

import com.jarqprog.artGallery.api.domains.DTOThin;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper=true)
public class RoleUserThin extends AbstractRoleUserDTO implements DTOThin {

    @NonNull
    @NotNull
    private String userLogin;

    public RoleUserThin(@NonNull SystemRole role, @NonNull String userLogin) {
        super(role);
        this.userLogin = userLogin;
    }
}
