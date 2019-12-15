package com.jarqprog.personapi.contact;

import com.jarqprog.domainperson.contact.ContactData;
import com.jarqprog.personapi.contact.dto.ApiContactDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ApiContactDTO> getAllContacts();
    <T extends ApiContactDTO> List<T> getAllContacts(Class<T> clazz);

    ApiContactDTO findContactById(long id);
    <T extends ApiContactDTO> T findContactById(long id, Class<T> clazz);

    long addContact(@NonNull ContactData contactData);

    void updateContact(long id, @NonNull ContactData contactData);

    void removeContact(long id);

}
