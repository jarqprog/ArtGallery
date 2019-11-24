package com.jarqprog.personapi.usecase.login;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.usecase.login.UserLoginDTO;

@JsonDeserialize(as= UserLoginDTOImpl.class)
public interface ApiUserLoginDTO extends UserLoginDTO {
}