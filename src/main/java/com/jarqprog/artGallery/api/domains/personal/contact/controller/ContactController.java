package com.jarqprog.artGallery.api.domains.personal.contact.controller;

import com.jarqprog.artGallery.api.domains.personal.contact.ContactService;
import com.jarqprog.artGallery.api.domains.personal.contact.dto.ContactDTO;
import com.jarqprog.artGallery.domain.personal.Contact;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact findContactById(@PathVariable("id") long id) {
        return contactService.findContactById(id);
    }

    @PostMapping
    public ResponseEntity addContact(@RequestBody ContactDTO contactDTO) {
        long id = contactService.addContact(contactDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateContact(@PathVariable("id") long id, @RequestBody ContactDTO contactDTO) {
        contactService.updateContact(id, contactDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeContact(@PathVariable("id") long id) {
        contactService.removeContact(id);
        return ResponseEntity.accepted().build();
    }
}
