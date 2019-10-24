package com.jarqprog.artGallery.rest.controller.lightDTOsREST;

import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.domain.dto.lightDto.ContactDTOLight;
import com.jarqprog.artGallery.springData.useCases.ContactService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/light/contacts")
public class ContactControllerLight {

    @NonNull private final ContactService contactService;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public ContactControllerLight(@NonNull ContactService contactService,
                                  @NonNull DtoConverter dtoConverter) {
        this.contactService = contactService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public List<ContactDTOLight> getAllContacts() {
        return contactService.getAllContacts()
                .stream()
                .map(h -> dtoConverter.convertHeavyToLight(h, ContactDTOLight.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContactDTOLight findContactById(@PathVariable("id") long id) {
        return dtoConverter
                .convertHeavyToLight(contactService.findContactById(id),
                        ContactDTOLight.class);

    }

    @PostMapping
    public ContactDTOLight addContact(@RequestBody ContactDTOLight contactDTO) {
        return dtoConverter
                .convertHeavyToLight(contactService.addContact(contactDTO),
                        ContactDTOLight.class);
    }

    @PutMapping("/{id}")
    public ContactDTOLight updateContact(@PathVariable("id") long id, @RequestBody ContactDTOLight contactDTO) {
        return dtoConverter
                .convertHeavyToLight(contactService.updateContact(id, contactDTO),
                        ContactDTOLight.class);
    }

    @DeleteMapping("/{id}")
    public void removeContact(@PathVariable("id") long id) {
        contactService.removeContact(id);
    }
}
