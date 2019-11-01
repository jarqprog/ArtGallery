package com.jarqprog.artGallery.api.domains.useCase.registration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistrationFormDTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String login;
    private String password;
}
