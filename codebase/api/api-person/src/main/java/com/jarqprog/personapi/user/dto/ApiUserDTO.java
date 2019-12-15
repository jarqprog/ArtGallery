package com.jarqprog.personapi.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.user.UserDTO;

@JsonDeserialize(as= UserThin.class)
public interface ApiUserDTO extends UserDTO {
}
