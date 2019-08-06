package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.exception.persistenceException.*;
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
    public ContactDTO findContactById(long id) {
        Contact contact = findById(id);
        return dtoEntityConverter.convertEntityToDto(contact, ContactDTO.class);
    }

    @Override
    public ContactDTO addContact(ContactDTO contactDTO) {
        ContactDTO dto;
        long id = contactDTO.getId();
        try {
            preventCreatingExistingContact(id);
            Contact contact = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
            Contact saved = contactRepository.save(contact);
            dto = dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
        } catch (EntityAlreadyExistsException e) {
            throw new CannotCreateEntityException(Contact.class, id, e);
        } catch (Exception ex) {
            throw new CannotCreateEntityException(Contact.class, id, ex.getMessage());
        }
        return dto;
    }

    @Override
    public ContactDTO updateContact(long id, ContactDTO contactDTO) throws EntityNotFoundException {
        ContactDTO dto;
        try {
            validateContactExists(id);
            contactDTO.setId(id);
            Contact updated = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
            Contact saved = contactRepository.save(updated);
            dto = dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
        } catch (CannotFindEntityException e) {
            throw new CannotUpdateEntityException(Contact.class, id, e);
        } catch (Exception ex) {
            throw new CannotUpdateEntityException(Contact.class, id, ex.getMessage());
        }
        return dto;
    }

    @Override
    public void removeContact(long id) throws EntityNotFoundException {
        try {
            findById(id);
            contactRepository.deleteById(id);
        } catch (CannotFindEntityException e) {
            throw new CannotRemoveEntityException(Contact.class, id, e);
        } catch (Exception ex) {
            throw new CannotRemoveEntityException(Contact.class, id, ex.getMessage());
        }
    }

    private Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new CannotFindEntityException(Contact.class, id));
    }

    private void validateContactExists(long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new CannotFindEntityException(Contact.class, contactId);
        }
    }

    private void preventCreatingExistingContact(long contactId) {
        if (contactRepository.existsById(contactId)) {
            throw new EntityAlreadyExistsException(Contact.class, contactId);
        }
    }
}
