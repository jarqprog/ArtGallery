package com.jarqprog.personapi.domains.contact.validation;

import com.jarqprog.domainperson.contact.ContactData;
import lombok.NonNull;

public interface ContactValidator {

    void validateOnCreation(final @NonNull ContactData contactData);
    void validateOnUpdate(final @NonNull ContactData contactData);
    void validateEmail(final @NonNull String email);
}
