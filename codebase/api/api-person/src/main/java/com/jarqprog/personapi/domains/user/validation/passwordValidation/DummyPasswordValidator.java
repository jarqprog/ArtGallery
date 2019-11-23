package com.jarqprog.personapi.domains.user.validation.passwordValidation;

import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.jarqprog.commonapi.constants.ApiConstants.*;


@Component
@Profile({DEV_PROFILE, TEST_PROFILE, HERO_PROFILE})
public class DummyPasswordValidator implements PasswordValidator {

    private static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    public boolean isValid(@NonNull final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && !password.contains(" ");
    }
}
