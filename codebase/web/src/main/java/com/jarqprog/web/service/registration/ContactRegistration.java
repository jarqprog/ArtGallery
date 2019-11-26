package com.jarqprog.web.service.registration;

import com.jarqprog.domainperson.contact.Contact;
import com.jarqprog.domainperson.registration.RegistrationFormDTO;
import lombok.NonNull;

public interface ContactRegistration {

    Contact createContactFromRegistration(@NonNull RegistrationFormDTO registrationFormDTO);
}
