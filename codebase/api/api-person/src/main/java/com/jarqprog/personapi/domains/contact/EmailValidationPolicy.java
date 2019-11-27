package com.jarqprog.personapi.domains.contact;

import java.util.regex.Pattern;

interface EmailValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
