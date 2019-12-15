package com.jarqprog.personapi.user;

import java.util.regex.Pattern;

interface LoginValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
