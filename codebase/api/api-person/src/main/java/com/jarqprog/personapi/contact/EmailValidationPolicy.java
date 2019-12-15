package com.jarqprog.personapi.contact;

import java.util.regex.Pattern;

interface EmailValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
