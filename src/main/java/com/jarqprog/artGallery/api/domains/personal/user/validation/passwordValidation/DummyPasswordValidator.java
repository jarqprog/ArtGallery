package com.jarqprog.artGallery.api.domains.personal.user.validation.passwordValidation;

import com.jarqprog.artGallery.api.ApiConstants;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({ApiConstants.DEV_PROFILE, ApiConstants.TEST_PROFILE})
public class DummyPasswordValidator implements PasswordValidator {

    private static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    public boolean isValid(@NonNull final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && !password.contains(" ");
    }
}
