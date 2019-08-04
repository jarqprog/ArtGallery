package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleContactService implements ContactService {

    @Autowired private ContactRepository contactRepository;

    @Autowired private DtoEntityConverter dtoEntityConverter;

    @Autowired private EntityMetadataService entityMetadataService;

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
        Contact contact = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
        Contact saved = contactRepository.save(contact);
        entityMetadataService.createMetadata(saved);
        return dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
    }

    @Override
    public ContactDTO updateContact(long id, ContactDTO contactDTO) throws EntityNotFoundException {
        findById(id);// throws exception if not founded
        contactDTO.setId(id);
        Contact updated = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
        Contact saved = contactRepository.save(updated);
        entityMetadataService.createMetadata(saved);
        return dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
    }

    @Override
    public boolean removeContact(long id) throws EntityNotFoundException {
        boolean isRemoved = false;
        try {
            Contact contact = findById(id);
            entityMetadataService.markDiscontinued(contact);
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
}
