package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.dto.PictureDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface PictureService {

    List<PictureDTO> getAllPictures();
    PictureDTO findById(long id) throws EntityNotFoundException;
    PictureDTO save(PictureDTO pictureDTO);
    PictureDTO update(long id, PictureDTO pictureDTO) throws EntityNotFoundException;
    boolean remove(long id) throws EntityNotFoundException;

}
