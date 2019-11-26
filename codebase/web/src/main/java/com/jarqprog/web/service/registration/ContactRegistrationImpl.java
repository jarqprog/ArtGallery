package com.jarqprog.web.service.registration;


import com.jarqprog.domainperson.contact.Contact;
import com.jarqprog.domainperson.contact.DomainContact;
import com.jarqprog.domainperson.registration.RegistrationFormDTO;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContactRegistrationImpl implements ContactRegistration {

    private static final Logger logger = LoggerFactory.getLogger(ContactRegistrationImpl.class);


    @Override
    public Contact createContactFromRegistration(@NonNull final RegistrationFormDTO registrationFormDTO) {
        final Contact contact = DomainContact.fromRegistration(registrationFormDTO);
//        long id = contactService.addContact(contact);
//        Contact registered = DomainContact.mergeID(id, contact);
//        logger.info("Contact added: {}", registered);
//        return registered;
        return contact;
    }
}
