package com.jarqprog.artGallery.api.domains.personal.contact.validation;

import com.jarqprog.artGallery.api.domains.personal.contact.policy.NamesValidationPolicy;
import com.jarqprog.artGallery.api.domains.personal.contact.policy.EmailValidationPolicy;
import com.jarqprog.artGallery.domain.personal.Contact;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

@Component
public class ContactValidatorImpl implements ContactValidator {

    @NonNull
    private final EmailValidationPolicy emailValidationPolicy;

    @NonNull
    private final NamesValidationPolicy namesValidationPolicy;


    @Autowired
    public ContactValidatorImpl(@NonNull final EmailValidationPolicy emailValidationPolicy,
                                @NonNull final NamesValidationPolicy namesValidationPolicy) {
        this.emailValidationPolicy = emailValidationPolicy;
        this.namesValidationPolicy = namesValidationPolicy;
    }

    @Override
    public void validateOnCreation(@NonNull final Contact contact) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateFirstName(contact, exceptionMessage);
        validateEmail(contact, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull final Contact contact) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateFirstName(contact, exceptionMessage);
        validateEmail(contact, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateEmail(@NonNull final String email) {
        StringBuilder exceptionMessage = new StringBuilder();
        if (StringUtils.isBlank(email)) {
            exceptionMessage.append("Email should not be blank. ");
        }
        final Matcher matcher = emailValidationPolicy.getPolicy().matcher(email);
        if (!matcher.matches()) {
            exceptionMessage.append("Email incorrect. ");
            exceptionMessage.append(emailValidationPolicy.getRestriction());
        }
        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    private StringBuilder validateFirstName(Contact contactDTO, StringBuilder exceptionMessage) {
        if (StringUtils.isBlank(contactDTO.getFirstName())) {
            exceptionMessage.append("First name cannot be empty. ");
        }
        final String firstName = contactDTO.getFirstName();
        final Matcher matcher = namesValidationPolicy.getPolicy().matcher(firstName);
        if (!matcher.matches()) {
            exceptionMessage.append("First Name is incorrect. ");
            exceptionMessage.append(namesValidationPolicy.getRestriction());
        }
        return exceptionMessage;
    }

    private StringBuilder validateEmail(Contact contactDTO, StringBuilder exceptionMessage) {
        final String email = contactDTO.getEmail();
        if (StringUtils.isBlank(email)) {
            exceptionMessage.append("Email should not be blank. ");
        }
        final Matcher matcher = emailValidationPolicy.getPolicy().matcher(email);
        if (!matcher.matches()) {
            exceptionMessage.append("Email incorrect. ");
            exceptionMessage.append(emailValidationPolicy.getRestriction());
        }
        return exceptionMessage;
    }

    private void throwExceptionIfMessageNotEmpty(StringBuilder exceptionMessage) {
        if (exceptionMessage.length() > 0) {
            exceptionMessage.insert(0, "Problem occurred on validation Contact: ");
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }


}
