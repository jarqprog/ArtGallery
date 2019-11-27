package com.jarqprog.personapi.domains.user;

import lombok.NonNull;

interface PasswordValidator {

    boolean isValid(@NonNull final String password);

}
