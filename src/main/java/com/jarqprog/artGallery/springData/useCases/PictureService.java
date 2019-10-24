package com.jarqprog.artGallery.springData.useCases;

import com.jarqprog.artGallery.domain.dto.heavyDto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.PictureDTOLight;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<PictureDTO> getAllPictures();
    PictureDTO findPictureById(long id);
    PictureDTO addPicture(@NonNull PictureDTOLight pictureDTO);
    PictureDTO updatePicture(long id, @NonNull PictureDTOLight pictureDTO);
    void removePicture(long id);

}
