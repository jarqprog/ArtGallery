package com.jarqprog.personapi.domains.roleUser.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.commonapi.absmodel.DTO;
import com.jarqprog.domainperson.model.user_role.RoleUserData;

@JsonDeserialize(as= RoleUserThin.class)
public interface RoleUserDTO extends RoleUserData, DTO {
}
