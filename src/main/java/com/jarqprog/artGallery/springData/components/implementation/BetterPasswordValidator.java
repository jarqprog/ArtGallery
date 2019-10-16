package com.jarqprog.artGallery.springData.components.implementation;

import com.jarqprog.artGallery.springData.components.PasswordValidator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Profile("prod")
public class BetterPasswordValidator implements PasswordValidator {

    private Pattern pattern;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    public BetterPasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean validate(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
