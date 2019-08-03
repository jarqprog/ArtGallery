package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.domain.User;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Slf4j
@Service
public class SimplePictureService implements PictureService {

    private final PictureRepository pictureRepository;
    private final EntityMetadataService entityMetadataService;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public SimplePictureService(PictureRepository pictureRepository,
                                EntityMetadataService entityMetadataService,
                                UserRepository userRepository,
                                ContactRepository contactRepository) {
        this.pictureRepository = pictureRepository;
        this.entityMetadataService = entityMetadataService;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public <P extends Picture> P save(P picture) {
        P created = pictureRepository.save(picture);
        entityMetadataService.create(created);  // todo maybe use aspect? jarq

//        Contact jarek = new Contact("jarek@jarek.com");
//        contactRepository.save(jarek);
//
//        User jaro = new User(jarek, "987987");
//        userRepository.save(jaro);
        return created;
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        boolean isRemoved = false;
        try {
            log.info("Removing picture with id: " + id);
            Picture picture = findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
            entityMetadataService.markDiscontinued(picture);
            pictureRepository.delete(picture);
            isRemoved = true;
            log.info("Picture removed");
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
        }
        return isRemoved;
    }

}
