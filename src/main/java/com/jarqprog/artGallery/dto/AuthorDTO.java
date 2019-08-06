package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthorDTO extends DTO {

    private String artisticNickname;
    private ContactDTO contact;
}
