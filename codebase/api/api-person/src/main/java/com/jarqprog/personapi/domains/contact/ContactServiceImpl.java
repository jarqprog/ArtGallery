package com.jarqprog.personapi.domains.contact;

import com.jarqprog.commonapi.components.DtoConverter;
import com.jarqprog.commonapi.exceptions.ResourceAlreadyExists;
import com.jarqprog.commonapi.exceptions.ResourceNotFoundException;
import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.contact.ContactData;
import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.personapi.domains.contact.dto.ContactDTO;
import com.jarqprog.personapi.domains.contact.dto.ContactThin;
import com.jarqprog.personapi.domains.contact.validation.ContactValidator;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

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
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, ContactThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends ContactDTO> List<T> getAllContacts(Class<T> clazz) {
        return contactRepository.findAll()
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findContactById(long id) {
        ContactEntity contact = findById(id);
        return dtoConverter.transformEntityTo(contact, ContactThin.class);
    }

    @Override
    public <T extends ContactDTO> T findContactById(long id, Class<T> clazz) {
        ContactEntity contact = findById(id);
        return dtoConverter.transformEntityTo(contact, clazz);
    }

    @Override
    public long addContact(@NonNull final ContactData contactData) {
        preventCreatingExistingContact(contactData.getId());

        contactValidator.validateOnCreation(contactData);

        final Contact contact = DomainContact.createWith()
                .firstName(contactData.getFirstName())
                .lastName(contactData.getLastName())
                .email(contactData.getEmail())
                .build();

        final ContactEntity contactEntity = ContactEntity.fromContact(contact);
        final ContactEntity saved = contactRepository.save(contactEntity);
        logger.info("Created contact with data: ID={}, first name={}", saved.getId(), saved.getFirstName());
        return saved.getId();
    }

    @Override
    public void updateContact(long id, @NonNull final ContactData contactData) {
        if (id != contactData.getId()) {
            throw new IllegalArgumentException("different contact's IDs were given");
        }
        contactValidator.validateOnUpdate(contactData);
        validateContactExists(id);

        final Contact contact = DomainContact.createWith()
                .id(contactData.getId())
                .version(contactData.getVersion())
                .firstName(contactData.getFirstName())
                .lastName(contactData.getLastName())
                .email(contactData.getEmail())
                .build();

        final ContactEntity contactEntity = ContactEntity.fromContact(contact);
        contactRepository.save(contactEntity);
    }

    @Override
    @Transactional
    public void removeContact(long id) {
        findById(id);
        //todo run query on Author DB to nullify contactId;
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
}
