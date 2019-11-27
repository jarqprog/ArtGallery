package com.jarqprog.personapi.domains.user;

import java.util.regex.Pattern;

interface LoginValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
