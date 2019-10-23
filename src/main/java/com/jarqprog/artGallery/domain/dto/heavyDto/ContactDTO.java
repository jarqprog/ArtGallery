package com.jarqprog.artGallery.domain.dto.heavyDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class ContactDTO extends DTO implements HeavyDTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
