package com.jarqprog.artGallery.api.domains.personal.user.validation.passwordValidation;

import lombok.NonNull;

public interface PasswordValidator {

    boolean isValid(@NonNull final String password);

}
