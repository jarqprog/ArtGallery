package com.jarqprog.artGallery.domain.dto.heavyDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleDTO extends DTO implements HeavyDTO {

    private Roles role;
}
