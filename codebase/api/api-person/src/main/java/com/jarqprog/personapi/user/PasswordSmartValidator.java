package com.jarqprog.personapi.user;

import org.springframework.lang.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordSmartValidator implements PasswordValidator {

    private final Pattern pattern;

    public PasswordSmartValidator(@NonNull final PasswordValidationPolicy policy) {
        pattern = policy.getPolicy();
    }

    @Override
    public boolean isValid(@NonNull final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
