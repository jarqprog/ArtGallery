package com.jarqprog.personapi.domains.roleUser.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.model.roleuser.RoleUserDTO;

@JsonDeserialize(as= RoleUserThin.class)
public interface ApiRoleUserDTO extends RoleUserDTO {
}
