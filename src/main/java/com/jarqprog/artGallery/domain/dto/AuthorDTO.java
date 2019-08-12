package com.jarqprog.artGallery.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class AuthorDTO extends DTO {

    private String artisticNickname;
    private ContactDTO contact;
}
