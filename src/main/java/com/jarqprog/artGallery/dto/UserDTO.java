package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO extends DTO {

    private ContactDTO contact;
    private String login;
    //todo add roles
}
