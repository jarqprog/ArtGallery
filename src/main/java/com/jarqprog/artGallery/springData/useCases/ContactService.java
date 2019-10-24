package com.jarqprog.artGallery.springData.useCases;

import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.ContactDTOLight;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<ContactDTO> getAllContacts();
    ContactDTO findContactById(long id);
    ContactDTO addContact(@NonNull ContactDTOLight contactDTO);
    ContactDTO updateContact(long id, @NonNull ContactDTOLight contactDTO);
    void removeContact(long id);

}
