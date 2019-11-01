package com.jarqprog.artGallery.api.domains.personal.contact;

import com.jarqprog.artGallery.domain.personal.ContactData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactData> getAllContacts();
    <T extends ContactData> List<ContactData> getAllContacts(Class<T> clazz);

    ContactData findContactById(long id);
    <T extends ContactData> T findContactById(long id, Class<T> clazz);

    long addContact(@NonNull ContactData contactData);

    void updateContact(long id, @NonNull ContactData contactData);

    void removeContact(long id);

}
