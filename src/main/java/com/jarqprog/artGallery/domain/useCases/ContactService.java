package com.jarqprog.artGallery.domain.useCases;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> getAllContacts();
    ContactDTO findContactById(long id);
    ContactDTO addContact(ContactDTO contactDTO);
    ContactDTO updateContact(long id, ContactDTO contactDTO);
    void removeContact(long id);

}
