package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.dto.PictureDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface PictureService {

    List<PictureDTO> getAllPictures();
    PictureDTO findPictureById(long id) throws EntityNotFoundException;
    PictureDTO addPicture(PictureDTO pictureDTO);
    PictureDTO updatePicture(long id, PictureDTO pictureDTO) throws EntityNotFoundException;
    boolean removePicture(long id) throws EntityNotFoundException;

}
