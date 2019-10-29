package com.jarqprog.artGallery.api.domains.personal.roleUser.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.RoleUser;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=true)
abstract class AbstractRoleUserDTO extends DomainDTO implements RoleUser {

    @NonNull
    @NotNull
    private SystemRole role;

    public AbstractRoleUserDTO(@NonNull SystemRole role) {
        this.role = role;
    }
}
