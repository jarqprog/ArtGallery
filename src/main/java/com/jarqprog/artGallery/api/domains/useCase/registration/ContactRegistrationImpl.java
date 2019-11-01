package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.domain.personal.ContactData;
import com.jarqprog.artGallery.api.domains.personal.contact.ContactService;
import com.jarqprog.artGallery.domain.personal.DomainContact;
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
    public Contact createContactFromRegistration(@NonNull final RegistrationFormDTO registrationFormDTO) {
        final Contact contact = DomainContact
                .createWith()
                .firstName(registrationFormDTO.getFirstName())
                .lastName(registrationFormDTO.getLastName())
                .nickname(registrationFormDTO.getNickname())
                .email(registrationFormDTO.getEmail())
                .build();
        contactService.addContact(contact);
        logger.info("Contact added: {}", contact);
        return contact;
    }
}
