package com.jarqprog.artGallery.springData.components.implementation;

import com.jarqprog.artGallery.springData.components.PasswordValidator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "test"})
public class DummyPasswordValidator implements PasswordValidator {

    private static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    public boolean validate(final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && !password.contains(" ");
    }
}
