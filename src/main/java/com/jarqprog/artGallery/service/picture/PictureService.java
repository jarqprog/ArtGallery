package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface PictureService {

    List<Picture> getAllPictures();
    Picture findById(Long id) throws EntityNotFoundException;
    <P extends Picture> Picture save(P picture);
    boolean remove(Long id);

}
