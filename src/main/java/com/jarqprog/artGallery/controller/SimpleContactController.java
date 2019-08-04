package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.ContactDTO;
import com.jarqprog.artGallery.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class SimpleContactController implements ContactController {

    @Autowired ContactService contactService;

    @Override
    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @Override
    @GetMapping("/{id}")
    public ContactDTO findById(@PathVariable("id") long id) {
        return contactService.findById(id);
    }

    @Override
    @PostMapping
    public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.add(contactDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable("id") long id, @RequestBody ContactDTO contactDTO) {
        return contactService.update(id, contactDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") long id) {
        contactService.remove(id);
    }
}
