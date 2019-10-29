package com.jarqprog.artGallery.api.domains.personal.contact;

import com.jarqprog.artGallery.api.domains.personal.contact.validation.ContactValidator;
import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.api.domains.personal.contact.dto.ContactThin;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @NonNull private final ContactRepository contactRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final ContactValidator contactValidator;

    @Autowired
    public ContactServiceImpl(@NonNull ContactRepository contactRepository,
                              @NonNull DtoConverter dtoConverter,
                              @NonNull ContactValidator contactValidator) {
        this.contactRepository = contactRepository;
        this.dtoConverter = dtoConverter;
        this.contactValidator = contactValidator;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToModel(c, ContactThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends Contact> List<Contact> getAllContacts(Class<T> clazz) {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToModel(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public Contact findContactById(long id) {
        ContactEntity contact = findById(id);
        return dtoConverter.convertEntityToModel(contact, ContactThin.class);
    }

    @Override
    public <T extends Contact> T findContactById(long id, Class<T> clazz) {
        ContactEntity contact = findById(id);
        return dtoConverter.convertEntityToModel(contact, clazz);
    }

    @Override
    public long addContact(@NonNull final Contact contact) {
        preventCreatingExistingContact(contact.getId());

        contactValidator.validateOnCreation(contact);

        final ContactEntity contactEntity = new ContactEntity();
        updateContactByDTO(contactEntity, contact);
        final ContactEntity saved = contactRepository.save(contactEntity);
        return saved.getId();
    }

    @Override
    public void updateContact(long id, @NonNull final Contact contact) {
        if (id != contact.getId()) {
            throw new IllegalArgumentException("different contact's IDs were given");
        }
        contactValidator.validateOnUpdate(contact);

        final ContactEntity contactEntity = findById(id);
        updateContactByDTO(contactEntity, contact);
        contactRepository.save(contactEntity);
    }

    @Override
    @Transactional
    public void removeContact(long id) {
        findById(id);
        //todo run query on Author DB to nullify contactId;
//        User user = findUserByContactId(id);
//        Author author = findAuthorByContactId(id);
//        user.setContact(null);
//        author.setContact(null);
//        userRepository.save(user);
//        authorRepository.save(author);
        contactRepository.deleteById(id);
    }

    private ContactEntity findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ContactEntity.class, id));
    }

    private void validateContactExists(long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new ResourceNotFoundException(ContactEntity.class, contactId);
        }
    }

    private void preventCreatingExistingContact(long contactId) {
        if (contactRepository.existsById(contactId)) {
            throw new ResourceAlreadyExists(ContactEntity.class, contactId);
        }
    }

    private void updateContactByDTO(ContactEntity contactEntity, Contact contact) {
        contactEntity.setVersion(contact.getVersion());
        contactEntity.setFirstName(contact.getFirstName());
        contactEntity.setLastName(contact.getLastName());
        contactEntity.setEmail(contact.getEmail());
        contactEntity.setNickname(contact.getNickname());
    }
}
