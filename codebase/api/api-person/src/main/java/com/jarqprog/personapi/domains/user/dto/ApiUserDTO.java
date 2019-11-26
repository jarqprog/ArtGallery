package com.jarqprog.personapi.domains.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.user.UserDTO;

@JsonDeserialize(as=UserThin.class)
public interface ApiUserDTO extends UserDTO {
}
