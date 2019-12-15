package com.jarqprog.personapi.user;

import lombok.NonNull;

interface PasswordValidator {

    boolean isValid(@NonNull final String password);

}
