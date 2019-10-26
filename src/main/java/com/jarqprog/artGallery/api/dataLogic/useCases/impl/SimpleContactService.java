package com.jarqprog.artGallery.api.dataLogic.useCases.impl;

import com.jarqprog.artGallery.api.dataLogic.components.dtoValidators.ContactValidator;
import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.thinDTO.ContactThin;
import com.jarqprog.artGallery.domain.entity.Author;
import com.jarqprog.artGallery.domain.entity.Contact;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.dataLogic.repositories.AuthorRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.ContactRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.UserRepository;
import com.jarqprog.artGallery.api.dataLogic.useCases.ContactService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleContactService implements ContactService {

    @NonNull private final ContactRepository contactRepository;
    @NonNull private final UserRepository userRepository;
    @NonNull private final AuthorRepository authorRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final ContactValidator contactValidator;

    @Autowired
    public SimpleContactService(@NonNull ContactRepository contactRepository,
                                @NonNull UserRepository userRepository,
                                @NonNull AuthorRepository authorRepository,
                                @NonNull DtoConverter dtoConverter,
                                @NonNull ContactValidator contactValidator) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.dtoConverter = dtoConverter;
        this.contactValidator = contactValidator;
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToDTO(c, ContactThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends ContactDTO> List<ContactDTO> getAllContacts(Class<T> clazz) {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToDTO(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findContactById(long id) {
        Contact contact = findById(id);
        return dtoConverter.convertEntityToDTO(contact, ContactThin.class);
    }

    @Override
    public <T extends ContactDTO> T findContactById(long id, Class<T> clazz) {
        Contact contact = findById(id);
        return dtoConverter.convertEntityToDTO(contact, clazz);
    }

    @Override
    public long addContact(@NonNull final ContactDTO contactDTO) {
        preventCreatingExistingContact(contactDTO.getId());

        contactValidator.validateOnCreation(contactDTO);

        final Contact contact = new Contact();
        updateContactByDTO(contact, contactDTO);
        final Contact saved = contactRepository.save(contact);
        return saved.getId();
    }

    @Override
    public void updateContact(long id, @NonNull final ContactDTO contactDTO) {
        if (id != contactDTO.getId()) {
            throw new IllegalArgumentException("different contact's IDs were given");
        }
        contactValidator.validateOnUpdate(contactDTO);

        final Contact contact = findById(id);
        updateContactByDTO(contact, contactDTO);
        contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void removeContact(long id) {
        findById(id);
//        User user = findUserByContactId(id);
//        Author author = findAuthorByContactId(id);
//        user.setContact(null);
//        author.setContact(null);
//        userRepository.save(user);
//        authorRepository.save(author);
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

    private void updateContactByDTO(Contact contact, ContactDTO contactDTO) {
        contact.setVersion(contactDTO.getVersion());
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setNickname(contactDTO.getNickname());
    }
}
