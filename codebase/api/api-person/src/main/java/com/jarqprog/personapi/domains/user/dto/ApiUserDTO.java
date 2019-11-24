package com.jarqprog.personapi.domains.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.domainperson.model.user.UserData;

@JsonDeserialize(as=UserThin.class)
public interface ApiUserDTO extends UserData {
}
