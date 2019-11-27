package com.jarqprog.personapi.contact;

import java.util.regex.Pattern;

interface NamesValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
