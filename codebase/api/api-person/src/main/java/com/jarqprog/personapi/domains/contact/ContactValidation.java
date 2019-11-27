package com.jarqprog.personapi.domains.contact;

import com.jarqprog.domainperson.contact.ContactData;
import lombok.NonNull;

interface ContactValidation {

    void validateOnCreation(final @NonNull ContactData contactData);
    void validateOnUpdate(final @NonNull ContactData contactData);
    void validateEmail(final @NonNull String email);
}
