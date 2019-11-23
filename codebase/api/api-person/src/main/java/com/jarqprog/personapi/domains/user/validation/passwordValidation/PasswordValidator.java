package com.jarqprog.personapi.domains.user.validation.passwordValidation;

import lombok.NonNull;

public interface PasswordValidator {

    boolean isValid(@NonNull final String password);

}
