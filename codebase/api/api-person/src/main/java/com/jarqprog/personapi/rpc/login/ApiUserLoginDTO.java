package com.jarqprog.personapi.rpc.login;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.login.UserLoginDTO;

@JsonDeserialize(as= UserLoginDTOImpl.class)
public interface ApiUserLoginDTO extends UserLoginDTO {
}
