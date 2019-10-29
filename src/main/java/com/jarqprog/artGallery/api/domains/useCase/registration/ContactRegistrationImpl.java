package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.api.domains.personal.contact.dto.ContactThin;
import com.jarqprog.artGallery.api.domains.personal.contact.ContactService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactRegistrationImpl implements ContactRegistration {

    private static final Logger logger = LoggerFactory.getLogger(ContactRegistrationImpl.class);

    @NonNull private final ContactService contactService;

    @Autowired
    public ContactRegistrationImpl(@NonNull ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public Contact createContactFromRegistration(@NonNull final RegistrationForm registrationForm) {
        final Contact contact = new ContactThin(registrationForm.getFirstName(), registrationForm.getEmail());
        contact.setLastName(registrationForm.getLastName());
        contact.setNickname(registrationForm.getNickname());
        long contactId = contactService.addContact(contact);
        contact.setId(contactId);
        logger.info("Contact added: {}", contact);
        return contact;
    }
}
