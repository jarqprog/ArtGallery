package com.jarqprog.personapi.domains.user;

import lombok.NonNull;


class PasswordDummyValidator implements PasswordValidator {

    private static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    public boolean isValid(@NonNull final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && !password.contains(" ");
    }
}
