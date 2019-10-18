package com.jarqprog.artGallery.springData.services;

import com.jarqprog.artGallery.domain.entity.Author;
import com.jarqprog.artGallery.domain.entity.Contact;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.springData.components.DtoEntityConverter;
import com.jarqprog.artGallery.springData.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.repository.AuthorRepository;
import com.jarqprog.artGallery.springData.repository.ContactRepository;
import com.jarqprog.artGallery.springData.repository.UserRepository;
import com.jarqprog.artGallery.domain.useCases.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleContactService implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public SimpleContactService(ContactRepository contactRepository,
                                UserRepository userRepository,
                                AuthorRepository authorRepository,
                                DtoEntityConverter dtoEntityConverter) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.dtoEntityConverter = dtoEntityConverter;
    }

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