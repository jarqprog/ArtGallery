package com.jarqprog.artGallery.service;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.TransactionRequiredException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class SimplePictureService implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public SimplePictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public <P extends Picture> P save(P picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        LocalDateTime discontinueDate = LocalDateTime.now();
        try {
            pictureRepository.deleteById(id, discontinueDate);
        } catch (TransactionRequiredException e) {
            // not used todo
        }
    }

}
