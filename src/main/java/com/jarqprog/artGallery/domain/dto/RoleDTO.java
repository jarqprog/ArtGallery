package com.jarqprog.artGallery.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jarqprog.artGallery.domain.dto.thinDTO.RoleThin;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;

import java.io.Serializable;

@JsonDeserialize(as = RoleThin.class)
public interface RoleDTO extends DTO, Serializable {

    AuthorizationRole getRole();
    void setRole(AuthorizationRole role);

}
