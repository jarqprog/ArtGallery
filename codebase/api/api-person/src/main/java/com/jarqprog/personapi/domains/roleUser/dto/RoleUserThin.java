package com.jarqprog.personapi.domains.roleUser.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import com.jarqprog.domainperson.model.SystemRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class RoleUserThin extends DomainDTO implements RoleUserDTO {

    private SystemRole role;
    private String userLogin;

}
