package com.jarqprog.domainperson.registration;

import com.jarqprog.commondomain.absmodel.DTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Builder
@ToString
@Accessors(fluent = true)
public class RegistrationFormDTO implements DTO {

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
}
