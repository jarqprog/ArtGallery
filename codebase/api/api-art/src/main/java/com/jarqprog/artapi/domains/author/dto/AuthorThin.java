package com.jarqprog.artapi.domains.author.dto;

import com.jarqprog.commonapi.absmodel.ApiDomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorThin extends ApiDomainDTO implements ApiAuthorDTO {

    private String artisticNickname = ANONYMOUS;
    private long contactId;

}
