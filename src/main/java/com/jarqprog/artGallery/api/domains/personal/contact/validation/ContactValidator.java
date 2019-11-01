package com.jarqprog.artGallery.api.domains.personal.contact.validation;

import com.jarqprog.artGallery.domain.personal.ContactData;
import lombok.NonNull;

public interface ContactValidator {

    void validateOnCreation(final @NonNull ContactData contactData);
    void validateOnUpdate(final @NonNull ContactData contactData);
    void validateEmail(final @NonNull String email);
}
