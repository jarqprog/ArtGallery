package com.jarqprog.artGallery.api.domains.artistic.picture;

import com.jarqprog.artGallery.domain.artistic.Picture;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<Picture> getAllPictures();
    <T extends Picture> List<T> getAllPictures(Class<T> clazz);

    Picture findPictureById(long id);
    <T extends Picture> T findPictureById(long id, Class<T> clazz);

    long addPicture(@NonNull Picture picture);

    void updatePicture(long id, @NonNull Picture picture);

    void removePicture(long id);
}
