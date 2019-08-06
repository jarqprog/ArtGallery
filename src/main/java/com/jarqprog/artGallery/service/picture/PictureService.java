package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.dto.PictureDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {

    List<PictureDTO> getAllPictures();
    PictureDTO findPictureById(long id);
    PictureDTO addPicture(PictureDTO pictureDTO);
    PictureDTO updatePicture(long id, PictureDTO pictureDTO);
    void removePicture(long id);

}
