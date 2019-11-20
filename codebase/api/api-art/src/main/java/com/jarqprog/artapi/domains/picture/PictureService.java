package com.jarqprog.artapi.domains.picture;

import com.jarqprog.artapi.domains.picture.dto.PictureDTO;
import com.jarqprog.artdomain.model.picture.PictureData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<PictureDTO> getAllPictures();
    <T extends PictureDTO> List<T> getAllPictures(Class<T> clazz);

    PictureDTO findPictureById(long id);
    <T extends PictureDTO> T findPictureById(long id, Class<T> clazz);

    long addPicture(@NonNull PictureData pictureData);

    void updatePicture(long id, @NonNull PictureData pictureData);

    void removePicture(long id);
}
