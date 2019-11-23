package com.jarqprog.personapi.domains.user.validation.passwordValidation;

import com.jarqprog.commonapi.constants.ApiConstants;
import com.jarqprog.personapi.domains.user.policy.PasswordValidationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Profile(ApiConstants.PROD_PROFILE)
public class BetterPasswordValidator implements PasswordValidator {

    private final Pattern pattern;

    @Autowired
    public BetterPasswordValidator(@NonNull final PasswordValidationPolicy policy) {
        pattern = policy.getPolicy();
    }

    @Override
    public boolean isValid(@NonNull final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
