package com.jarqprog.artGallery.api.rest.controller;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.ContactService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @NonNull private final ContactService contactService;

    @Autowired
    public ContactController(@NonNull ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO findContactById(@PathVariable("id") long id) {
        return contactService.findContactById(id);
    }

    @PostMapping
    public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.addContact(contactDTO);
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable("id") long id, @RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(id, contactDTO);
    }

    @DeleteMapping("/{id}")
    public void removeContact(@PathVariable("id") long id) {
        contactService.removeContact(id);
    }
}
