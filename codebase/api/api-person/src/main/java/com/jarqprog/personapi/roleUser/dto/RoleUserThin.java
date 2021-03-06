package com.jarqprog.personapi.roleUser.dto;

import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import com.jarqprog.domainperson.SystemRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class RoleUserThin extends ApiDomainDTO implements ApiRoleUserDTO {

    private SystemRole role;
    private String userLogin;

}
