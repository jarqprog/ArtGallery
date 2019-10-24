package com.jarqprog.artGallery.domain.dto.lightDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ContactDTOLight extends DTO implements LightDTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
