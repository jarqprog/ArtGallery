package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.dto.ContactDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> getAllContacts();
    ContactDTO findContactById(long id) throws EntityNotFoundException;
    ContactDTO addContact(ContactDTO contactDTO);
    ContactDTO updateContact(long id, ContactDTO contactDTO) throws EntityNotFoundException;
    boolean removeContact(long id) throws EntityNotFoundException;

}
