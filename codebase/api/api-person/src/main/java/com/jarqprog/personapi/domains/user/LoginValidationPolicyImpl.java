package com.jarqprog.personapi.domains.user;

import java.util.regex.Pattern;

class LoginValidationPolicyImpl implements LoginValidationPolicy {

    private static final String RESTRICTION = "1) can only start and end with letter\n" +
            "2) length is between 5 and 14 characters\n" +
            "3) can contain letters and dash(-)";

    private static final String REGEX = "(?i)[a-z]([-',.a-z]{3,12}[a-z])?";

    @Override
    public String getRestriction() {
        return RESTRICTION;
    }

    @Override
    public Pattern getPolicy() {
        return Pattern.compile(REGEX);
    }


}
