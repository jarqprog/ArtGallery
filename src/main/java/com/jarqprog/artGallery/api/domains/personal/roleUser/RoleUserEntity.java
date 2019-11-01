package com.jarqprog.artGallery.api.domains.personal.roleUser;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.api.domains.personal.user.UserEntity;
import com.jarqprog.artGallery.domain.personal.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Access(AccessType.FIELD)
@Table(name="role_user")
public class RoleUserEntity extends DomainEntity implements RoleUser {


    public static RoleUserEntity fromRoleUser(@NonNull final RoleUser roleUser) {
        return new RoleUserEntity(roleUser);
    }

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private SystemRole role;

    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_login", referencedColumnName = "login")
    private UserEntity userEntity;

    @Override
    @Transient
    public UserEntity getUser() {
        return userEntity;
    }

    private RoleUserEntity(RoleUser roleUser) {
        this(roleUser.getId(), roleUser);
    }

    private RoleUserEntity(long id, RoleUser roleUser) {
        this(id,
            roleUser.getVersion(),
            roleUser.getRole(),
            roleUser.getUser());
    }

    private RoleUserEntity(long id, int version, @NonNull SystemRole role, @NonNull User user) {
        super(id, version);
        this.role = role;
        this.userEntity = UserEntity.fromUser(user);
    }

    @Override
    @Transient
    public String getUserLogin() {
        assert userEntity != null;
        return userEntity.getLogin();
    }
}
