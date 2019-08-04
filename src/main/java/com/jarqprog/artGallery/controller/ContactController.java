package com.jarqprog.artGallery.controller;


import com.jarqprog.artGallery.dto.ContactDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ContactController {

    List<ContactDTO> getAllContacts();
    ContactDTO findById(@PathVariable("id") long id);
    ContactDTO addContact(@RequestBody ContactDTO contactDTO);
    ContactDTO updateContact(@PathVariable("id") long id, @RequestBody ContactDTO contactDTO);
    void remove(@PathVariable("id") long id);
}
