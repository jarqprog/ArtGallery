package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SimpleContactService implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactDTO> getAllContacts() {
        return null;
    }

    @Override
    public ContactDTO findById(long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        return null;
    }

    @Override
    public boolean remove(long id) {
        return false;
    }
}
