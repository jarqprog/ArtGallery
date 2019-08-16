package com.jarqprog.artGallery.rest.controller;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.useCases.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class SimpleContactController {

    @Autowired ContactService contactService;

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
