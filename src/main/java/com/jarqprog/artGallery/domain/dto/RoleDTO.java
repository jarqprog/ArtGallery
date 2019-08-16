package com.jarqprog.artGallery.domain.dto;

import com.jarqprog.artGallery.domain.entity.Roles;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleDTO extends DTO {

    private Roles role;
}
