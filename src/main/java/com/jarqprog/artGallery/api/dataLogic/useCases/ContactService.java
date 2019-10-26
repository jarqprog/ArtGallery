package com.jarqprog.artGallery.api.dataLogic.useCases;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> getAllContacts();
    <T extends ContactDTO> List<ContactDTO> getAllContacts(Class<T> clazz);

    ContactDTO findContactById(long id);
    <T extends ContactDTO> T findContactById(long id, Class<T> clazz);

    long addContact(@NonNull ContactDTO contactDTO);

    void updateContact(long id, @NonNull ContactDTO contactDTO);

    void removeContact(long id);

}
