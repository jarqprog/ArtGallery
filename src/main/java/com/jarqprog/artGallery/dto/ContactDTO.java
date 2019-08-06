package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContactDTO implements DTO {

    private long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
