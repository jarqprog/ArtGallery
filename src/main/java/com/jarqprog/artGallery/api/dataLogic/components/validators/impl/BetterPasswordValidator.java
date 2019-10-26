package com.jarqprog.artGallery.api.dataLogic.components.validators.impl;

import com.jarqprog.artGallery.api.dataLogic.components.validators.PasswordValidator;
import com.jarqprog.artGallery.api.dataLogic.components.validationPolicy.PasswordValidationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Profile("prod")
public class BetterPasswordValidator implements PasswordValidator {

    private final Pattern pattern;

    @Autowired
    public BetterPasswordValidator(@NonNull final PasswordValidationPolicy policy) {
        pattern = policy.getPolicy();
    }

    @Override
    public boolean validate(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
