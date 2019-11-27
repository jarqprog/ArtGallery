package com.jarqprog.personapi.domains.contact;

import java.util.regex.Pattern;

interface NamesValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
