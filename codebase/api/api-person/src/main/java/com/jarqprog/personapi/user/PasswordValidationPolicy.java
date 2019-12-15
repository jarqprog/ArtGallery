package com.jarqprog.personapi.user;

import java.util.regex.Pattern;

interface PasswordValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
