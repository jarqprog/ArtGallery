package com.jarqprog.personapi.domains.contact;

import com.jarqprog.domainperson.model.contact.ContactData;
import com.jarqprog.personapi.domains.contact.dto.ContactDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> getAllContacts();
    <T extends ContactDTO> List<T> getAllContacts(Class<T> clazz);

    ContactDTO findContactById(long id);
    <T extends ContactDTO> T findContactById(long id, Class<T> clazz);

    long addContact(@NonNull ContactData contactData);

    void updateContact(long id, @NonNull ContactData contactData);

    void removeContact(long id);

}
