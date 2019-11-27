package com.jarqprog.personapi.domains.user;

import java.util.regex.Pattern;

interface PasswordValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
