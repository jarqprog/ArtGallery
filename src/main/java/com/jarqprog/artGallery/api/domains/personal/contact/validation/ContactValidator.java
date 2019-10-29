package com.jarqprog.artGallery.api.domains.personal.contact.validation;

import com.jarqprog.artGallery.domain.personal.Contact;
import lombok.NonNull;

public interface ContactValidator {

    void validateOnCreation(final @NonNull Contact contact);
    void validateOnUpdate(final @NonNull Contact contact);
    void validateEmail(final @NonNull String email);
}
