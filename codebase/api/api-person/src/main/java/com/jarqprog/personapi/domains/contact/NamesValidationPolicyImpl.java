package com.jarqprog.personapi.domains.contact;

import java.util.regex.Pattern;


class NamesValidationPolicyImpl implements NamesValidationPolicy {

    private static final String RESTRICTION = "1) can only start and end with letter\n" +
            "2) length is between 1 and 25 characters\n" +
            "3) can contain letters and characters: dash(-), space( )";

    private static final String REGEX = "(?i)[a-z]([- ',.a-z]{0,23}[a-z])?";

    @Override
    public String getRestriction() {
        return RESTRICTION;
    }

    @Override
    public Pattern getPolicy() {
        return Pattern.compile(REGEX);
    }
}
