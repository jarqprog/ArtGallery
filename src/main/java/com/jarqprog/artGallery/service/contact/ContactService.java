package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.dto.ContactDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> getAllContacts();
    ContactDTO findById(long id) throws EntityNotFoundException;
    ContactDTO save(ContactDTO contactDTO);
    boolean remove(long id);

}
