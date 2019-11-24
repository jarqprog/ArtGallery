package com.jarqprog.domainperson.usecase.registration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistrationFormDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
}
