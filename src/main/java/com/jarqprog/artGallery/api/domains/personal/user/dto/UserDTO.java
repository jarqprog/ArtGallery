package com.jarqprog.artGallery.api.domains.personal.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.domain.personal.UserData;

@JsonDeserialize(as=UserThin.class)
public interface UserDTO extends UserData, DTO {
}