package com.jarqprog.artGallery.domain.dto.useCaseDTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationDTO {

    private static final int MIN_LOGIN_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 4;

    @NotNull private String firstName;
    private String lastName;
    private String nickname;
    @NotNull @Email private String email;
    @NotNull @Size(min=MIN_LOGIN_LENGTH) private String login;
    @NotNull @Size(min=MIN_PASSWORD_LENGTH) private String password;
}
