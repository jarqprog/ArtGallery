package com.jarqprog.artGallery.api.dataLogic.useCases;

import com.jarqprog.artGallery.domain.dto.PictureDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<PictureDTO> getAllPictures();
    <T extends PictureDTO> List<T> getAllPictures(Class<T> clazz);

    PictureDTO findPictureById(long id);
    <T extends PictureDTO> T findPictureById(long id, Class<T> clazz);

    long addPicture(@NonNull PictureDTO pictureDTO);

    void updatePicture(long id, @NonNull PictureDTO pictureDTO);

    void removePicture(long id);
}
