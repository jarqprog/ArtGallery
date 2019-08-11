package com.jarqprog.artGallery.controller.rest;

import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class SimpleContactController implements ContactController {

    @Autowired ContactService contactService;

    @Override
    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @Override
    @GetMapping("/{id}")
    public ContactDTO findContactById(@PathVariable("id") long id) {
        return contactService.findContactById(id);
    }

    @Override
    @PostMapping
    public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.addContact(contactDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable("id") long id, @RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(id, contactDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void removeContact(@PathVariable("id") long id) {
        contactService.removeContact(id);
    }
}
