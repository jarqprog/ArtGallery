package com.jarqprog.artapi.domains.author.dto;

import com.jarqprog.commonapi.absmodel.DomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorThin extends DomainDTO implements AuthorDTO {

    private String artisticNickname = ANONYMOUS;
    private long contactId;

}
