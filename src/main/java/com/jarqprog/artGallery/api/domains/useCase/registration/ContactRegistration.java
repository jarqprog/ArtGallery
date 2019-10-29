package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.api.domains.useCase.registration.RegistrationForm;
import lombok.NonNull;

public interface ContactRegistration {

    Contact createContactFromRegistration(@NonNull RegistrationForm registrationForm);
}
