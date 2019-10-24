package com.jarqprog.artGallery.domain.dto.lightDto;

import com.jarqprog.artGallery.domain.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorDTOLight extends DTO implements LightDTO {

    private String artisticNickname;
    private long contactId;
}
