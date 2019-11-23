package com.jarqprog.personapi.domains.contact.policy;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidationPolicyImpl implements EmailValidationPolicy {

    private static final String RESTRICTION = "1) A-Z characters allowed\n" +
            "2) a-z characters allowed\n" +
            "3) 0-9 numbers allowed\n" +
            "4) Additionally email may contain only dot(.), dash(-) and underscore(_)\n" +
            "5) Rest all characters are not allowed";

    private static final String REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public String getRestriction() {
        return RESTRICTION;
    }

    @Override
    public Pattern getPolicy() {
        return Pattern.compile(REGEX);
    }
}
