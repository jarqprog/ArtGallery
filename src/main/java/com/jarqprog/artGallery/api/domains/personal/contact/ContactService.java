package com.jarqprog.artGallery.api.domains.personal.contact;

import com.jarqprog.artGallery.domain.personal.Contact;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<Contact> getAllContacts();
    <T extends Contact> List<Contact> getAllContacts(Class<T> clazz);

    Contact findContactById(long id);
    <T extends Contact> T findContactById(long id, Class<T> clazz);

    long addContact(@NonNull Contact contact);

    void updateContact(long id, @NonNull Contact contact);

    void removeContact(long id);

}
