package com.jarqprog.personapi.domains.user.policy;

import java.util.regex.Pattern;

public interface LoginValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
