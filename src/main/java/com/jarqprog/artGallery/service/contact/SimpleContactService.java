package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleContactService implements ContactService {

    @Autowired private ContactRepository contactRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;


    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoEntityConverter.convertEntityToDto(c, ContactDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findContactById(long id) throws EntityNotFoundException {
        Contact contact = findById(id);
        return dtoEntityConverter.convertEntityToDto(contact, ContactDTO.class);
    }

    @Override
    public ContactDTO addContact(ContactDTO contactDTO) {
        if (contactRepository.existsById(contactDTO.getId())) {
            //throw exception here
        }
        Contact contact = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
        Contact saved = contactRepository.save(contact);
        return dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
    }

    @Override
    public ContactDTO updateContact(long id, ContactDTO contactDTO) throws EntityNotFoundException {
        validateContactExists(id);
        contactDTO.setId(id);
        Contact updated = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
        Contact saved = contactRepository.save(updated);
        return dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
    }

    @Override
    public boolean removeContact(long id) throws EntityNotFoundException {
        boolean isRemoved = false;
        try {
            Contact contact = findById(id);
            contactRepository.delete(contact);
            isRemoved = true;
        } catch (EntityNotFoundException e) {
            //todo
        }
        return isRemoved;
    }

    private Contact findById(Long id) throws EntityNotFoundException {
        return contactRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    private void validateContactExists(long contactId) throws EntityNotFoundException {
        if (!contactRepository.existsById(contactId)) {
            throw new EntityNotFoundException();
        }
    }
}
