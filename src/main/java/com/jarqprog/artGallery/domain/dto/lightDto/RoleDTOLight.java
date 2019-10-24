package com.jarqprog.artGallery.domain.dto.lightDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleDTOLight extends DTO implements LightDTO {

    private Roles role;
}
