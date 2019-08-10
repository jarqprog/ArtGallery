package com.jarqprog.artGallery.service.contact;

import com.jarqprog.artGallery.domain.Author;
import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.domain.User;
import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.exception.persistenceException.*;
import com.jarqprog.artGallery.repository.AuthorRepository;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleContactService implements ContactService {

    @Autowired private ContactRepository contactRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;


    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
        preventCreatingExistingContact(contactDTO.getId());
        Contact contact = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
        Contact saved = contactRepository.save(contact);
        return dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
    }

    @Override
    public ContactDTO updateContact(long id, ContactDTO contactDTO) {
        validateContactExists(id);
        contactDTO.setId(id);
        Contact updated = dtoEntityConverter.convertDtoToEntity(contactDTO, Contact.class);
        Contact saved = contactRepository.save(updated);
        return dtoEntityConverter.convertEntityToDto(saved, ContactDTO.class);
    }

    @Override
    @Transactional
    public void removeContact(long id) {
        findById(id);
        User user = findUserByContactId(id);
        Author author = findAuthorByContactId(id);
        user.setContact(null);
        author.setContact(null);
        userRepository.save(user);
        authorRepository.save(author);
        contactRepository.deleteById(id);
    }

    private Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Contact.class, id));
    }

    private User findUserByContactId(Long contactId) {
        return userRepository.findUserByContactId(contactId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class));
    }

    private Author findAuthorByContactId(Long contactId) {
        return authorRepository.findAuthorByContactId(contactId)
                .orElseThrow(() -> new ResourceNotFoundException(Author.class));
    }

    private void validateContactExists(long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new ResourceNotFoundException(Contact.class, contactId);
        }
    }

    private void preventCreatingExistingContact(long contactId) {
        if (contactRepository.existsById(contactId)) {
            throw new ResourceAlreadyExists(Contact.class, contactId);
        }
    }
}
