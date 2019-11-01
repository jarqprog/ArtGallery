package com.jarqprog.artGallery.api.domains.artistic.picture;

import com.jarqprog.artGallery.domain.artistic.PictureData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<PictureData> getAllPictures();
    <T extends PictureData> List<T> getAllPictures(Class<T> clazz);

    PictureData findPictureById(long id);
    <T extends PictureData> T findPictureById(long id, Class<T> clazz);

    long addPicture(@NonNull PictureData pictureData);

    void updatePicture(long id, @NonNull PictureData pictureData);

    void removePicture(long id);
}
