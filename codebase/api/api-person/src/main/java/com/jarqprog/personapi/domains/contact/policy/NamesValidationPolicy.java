package com.jarqprog.personapi.domains.contact.policy;

import java.util.regex.Pattern;

public interface NamesValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
