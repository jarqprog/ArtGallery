package com.jarqprog.personapi.domains.contact;

import com.jarqprog.domainperson.contact.ContactData;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;


class ContactValidationImpl implements ContactValidation {

    @NonNull
    private final EmailValidationPolicy emailValidationPolicy;

    @NonNull
    private final NamesValidationPolicy namesValidationPolicy;


    ContactValidationImpl(@NonNull final EmailValidationPolicy emailValidationPolicy,
                                 @NonNull final NamesValidationPolicy namesValidationPolicy) {
        this.emailValidationPolicy = emailValidationPolicy;
        this.namesValidationPolicy = namesValidationPolicy;
    }

    @Override
    public void validateOnCreation(@NonNull final ContactData contactData) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateFirstName(contactData, exceptionMessage);
        validateEmail(contactData, exceptionMessage);

        throwExceptionIfMessageNotEmpty(exceptionMessage);
    }

    @Override
    public void validateOnUpdate(@NonNull final ContactData contactData) {
        StringBuilder exceptionMessage = new StringBuilder();
        validateFirstName(contactData, exceptionMessage);
        validateEmail(contactData, exceptionMessage);

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

    private StringBuilder validateFirstName(ContactData contactDTO, StringBuilder exceptionMessage) {
        if (StringUtils.isNotEmpty(contactDTO.getFirstName())) {
            final String firstName = contactDTO.getFirstName();
            final Matcher matcher = namesValidationPolicy.getPolicy().matcher(firstName);
            if (!matcher.matches()) {
                exceptionMessage.append("First Name is incorrect. ");
                exceptionMessage.append(namesValidationPolicy.getRestriction());
            }
        }
        return exceptionMessage;
    }

    private StringBuilder validateEmail(ContactData contactDTO, StringBuilder exceptionMessage) {
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
