package com.jarqprog.artGallery.api.domains.personal.roleUser;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.api.domains.personal.user.UserEntity;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="role_user")
public class RoleUserEntity extends DomainEntity {

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private SystemRole role;

    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_login", referencedColumnName = "login")
    private UserEntity user;
}
