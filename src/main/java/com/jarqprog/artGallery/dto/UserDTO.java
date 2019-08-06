package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO implements DTO {

    private long id;
    private ContactDTO contact;
    private String login;
    //todo add roles
}
