package com.jarqprog.artGallery.api.domains.artistic.author.dto;

import com.jarqprog.artGallery.api.domains.DomainDTO;
import com.jarqprog.artGallery.domain.artistic.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorFat extends DomainDTO implements AuthorDTO, Author {

    private String artisticNickname = ANONYMOUS;
    private long contactId;

}
