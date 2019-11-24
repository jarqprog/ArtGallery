package com.jarqprog.web.service.registration;


import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.domainperson.usecase.registration.RegistrationFormDTO;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContactRegistrationImpl implements ContactRegistration {

    private static final Logger logger = LoggerFactory.getLogger(ContactRegistrationImpl.class);


    @Override
    public Contact createContactFromRegistration(@NonNull final RegistrationFormDTO registrationFormDTO) {
        final Contact contact = DomainContact
                .createWith()
                .firstName(registrationFormDTO.getFirstName())
                .lastName(registrationFormDTO.getLastName())
                .email(registrationFormDTO.getEmail())
                .build();
//        long id = contactService.addContact(contact);
//        Contact registered = DomainContact.mergeID(id, contact);
//        logger.info("Contact added: {}", registered);
//        return registered;
        return contact;
    }
}
