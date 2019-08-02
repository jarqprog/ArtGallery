package com.jarqprog.artGallery.service;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SimplePictureService implements PictureService {

    private PictureRepository pictureRepository;

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

}
