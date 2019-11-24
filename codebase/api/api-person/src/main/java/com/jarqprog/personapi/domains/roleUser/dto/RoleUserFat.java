package com.jarqprog.personapi.domains.roleUser.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.roleuser.RoleUser;
import com.jarqprog.personapi.domains.user.dto.UserFat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
