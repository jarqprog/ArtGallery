package com.jarqprog.artGallery.domain.dto.thinDTO;

import com.jarqprog.artGallery.domain.dto.AuthorDTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorThin extends DomainDTO implements AuthorDTO, DTOThin {

    private String artisticNickname;
    private long contactId;

}
