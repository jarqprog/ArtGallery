package com.jarqprog.personapi.read.readuser;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.readuser.ReadUserDTO;

@JsonDeserialize(as= ApiReadUserDTOImpl.class)
public interface ApiReadUserDTO extends ReadUserDTO {
}
