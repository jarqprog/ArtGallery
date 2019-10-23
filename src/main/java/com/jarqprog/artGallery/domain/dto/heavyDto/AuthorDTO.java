package com.jarqprog.artGallery.domain.dto.heavyDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorDTO extends DTO implements HeavyDTO {

    private String artisticNickname;
    private ContactDTO contact;
}
