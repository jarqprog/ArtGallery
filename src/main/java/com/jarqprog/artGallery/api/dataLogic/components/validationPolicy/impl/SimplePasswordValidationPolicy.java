package com.jarqprog.artGallery.api.dataLogic.components.validationPolicy.impl;

import com.jarqprog.artGallery.api.dataLogic.components.validationPolicy.PasswordValidationPolicy;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class SimplePasswordValidationPolicy implements PasswordValidationPolicy {

    private static final String RESTRICTION = "1) Be between 8 and 40 characters long\n" +
            "2) Contain at least one digit.\n" +
            "3) Contain at least one lower case character.\n" +
            "4) Contain at least one upper case character.\n" +
            "5) Contain at least on special character from [ @ # $ % ! . ].";

    private static final String REGEX = "((?=.*[a-z])(?=.*\\\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    @Override
    public String getRestriction() {
        return RESTRICTION;
    }

    @Override
    public Pattern getPolicy() {
        return Pattern.compile(REGEX);
    }
}
