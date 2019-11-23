package com.jarqprog.personapi.domains.contact.policy;

import java.util.regex.Pattern;

public interface EmailValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
