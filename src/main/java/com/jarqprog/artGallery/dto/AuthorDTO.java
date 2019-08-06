package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthorDTO implements DTO {

    private long id;
    private String artisticNickname;
    private ContactDTO contact;
}
