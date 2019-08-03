package com.jarqprog.artGallery.service;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.repository.PictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SimplePictureService implements PictureService {

    private final PictureRepository pictureRepository;
    private final EntityMetadataService entityMetadataService;

    @Autowired
    public SimplePictureService(PictureRepository pictureRepository,
                                EntityMetadataService entityMetadataService) {
        this.pictureRepository = pictureRepository;
        this.entityMetadataService = entityMetadataService;
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public <P extends Picture> P save(P picture) {
        P created = pictureRepository.save(picture);
        entityMetadataService.create(created);  // todo maybe use aspect? jarq
        return created;
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        boolean isRemoved = false;
//        try {
//            Picture picture = findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
//            picture.setDiscontinueDate(LocalDateTime.now());
//            save(picture);
//            log.info(picture + " is removed");
//            isRemoved = true;
//        } catch (EntityNotFoundException e) {
//            log.error("Not found picture with id: " + id);
//        }
        return isRemoved;
    }

}
