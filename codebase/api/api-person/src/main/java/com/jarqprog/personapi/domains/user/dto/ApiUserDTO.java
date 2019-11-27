package com.jarqprog.personapi.domains.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.user.UserDTO;
import com.jarqprog.personapi.domains.user.dto.UserThin;

@JsonDeserialize(as= UserThin.class)
public interface ApiUserDTO extends UserDTO {
}
