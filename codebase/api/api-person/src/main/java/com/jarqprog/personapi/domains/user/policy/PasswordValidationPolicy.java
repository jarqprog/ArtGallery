package com.jarqprog.personapi.domains.user.policy;

import java.util.regex.Pattern;

public interface PasswordValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
