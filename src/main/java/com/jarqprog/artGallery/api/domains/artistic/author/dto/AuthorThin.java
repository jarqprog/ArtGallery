package com.jarqprog.artGallery.api.domains.artistic.author.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorThin extends DomainDTO implements AuthorDTO {

    private String artisticNickname = ANONYMOUS;
    private long contactId;

}
