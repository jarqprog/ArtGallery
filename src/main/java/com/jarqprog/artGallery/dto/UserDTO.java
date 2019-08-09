package com.jarqprog.artGallery.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class UserDTO extends DTO {

    private ContactDTO contact;
    private String login;
    //todo add roles? do I need to expose it?
}
