package com.jarqprog.artapi.domains.picture;

import com.jarqprog.artapi.domains.picture.dto.ApiPictureDTO;
import com.jarqprog.artdomain.picture.PictureData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<ApiPictureDTO> getAllPictures();
    <T extends ApiPictureDTO> List<T> getAllPictures(Class<T> clazz);

    ApiPictureDTO findPictureById(long id);
    <T extends ApiPictureDTO> T findPictureById(long id, Class<T> clazz);

    long addPicture(@NonNull PictureData pictureData);

    void updatePicture(long id, @NonNull PictureData pictureData);

    void removePicture(long id);
}
