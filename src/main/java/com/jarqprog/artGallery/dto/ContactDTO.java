package com.jarqprog.artGallery.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class ContactDTO extends DTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
