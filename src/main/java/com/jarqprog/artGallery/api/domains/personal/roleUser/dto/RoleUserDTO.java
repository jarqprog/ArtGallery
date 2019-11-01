package com.jarqprog.artGallery.api.domains.personal.roleUser.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.domain.personal.RoleUserData;

@JsonDeserialize(as= RoleUserThin.class)
public interface RoleUserDTO extends RoleUserData, DTO {
}
