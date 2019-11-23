package com.jarqprog.domainperson.model.user_role;

import com.jarqprog.commondomain.absmodel.DomainModel;
import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.user.User;
import lombok.*;


@Value
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DomainRoleUser extends DomainModel implements RoleUser {

    public static DomainRoleUser mergeID(long id, @NonNull final RoleUser roleUser) {
        assert id > 0;
        return new DomainRoleUser(id, roleUser);
    }

    public static DomainRoleUser withRoleUser(@NonNull final RoleUser roleUser) {
        return new DomainRoleUser(roleUser);
    }

    @Builder(builderMethodName = "createWith")
    public static DomainRoleUser buildWithData(long id, int version,
                                               @NonNull SystemRole role,
                                               @NonNull User user) {
        return new DomainRoleUser(id, version, role, user);
    }


    @NonNull
    private final SystemRole role;

    @NonNull
    private final User user;



    private DomainRoleUser(long id, RoleUser roleUser) {
        this(id, roleUser.getVersion(), roleUser.getRole(), roleUser.getUser());
    }

    private DomainRoleUser(RoleUser roleUser) {
        this(roleUser.getId(), roleUser);
    }

    private DomainRoleUser(long id, int version, @NonNull SystemRole role, @NonNull User user) {
        super(id, version);
        this.role = role;
        this.user = user;
    }

    @Override
    public String getUserLogin() {
        assert user != null;
        return user.getLogin();
    }
}
