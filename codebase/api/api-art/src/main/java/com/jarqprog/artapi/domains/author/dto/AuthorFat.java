package com.jarqprog.artapi.domains.author.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import com.jarqprog.artdomain.model.author.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorFat extends DomainDTO implements AuthorDTO, Author {

    private String artisticNickname = ANONYMOUS;
    private long contactId;

}
