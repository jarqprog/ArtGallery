package com.jarqprog.web.service.registration;

import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.usecase.registration.RegistrationFormDTO;
import lombok.NonNull;

public interface ContactRegistration {

    Contact createContactFromRegistration(@NonNull RegistrationFormDTO registrationFormDTO);
}
