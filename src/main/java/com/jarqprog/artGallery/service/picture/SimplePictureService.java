package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    @Autowired private PictureRepository pictureRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;


    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoEntityConverter.convertEntityToDto(p, PictureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findPictureById(long id) throws EntityNotFoundException {
        Picture picture = findById(id);
        return dtoEntityConverter.convertEntityToDto(picture, PictureDTO.class);
    }

    @Override
    public PictureDTO addPicture(PictureDTO pictureDTO) {
        if (pictureRepository.existsById(pictureDTO.getId())) {
            //todo throw exception
        }
        Picture picture = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(picture);
        return dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    public PictureDTO updatePicture(long id, PictureDTO pictureDTO) throws EntityNotFoundException {
        validatePictureExists(id);
        pictureDTO.setId(id);
        Picture updated = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(updated);
        return dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    public boolean removePicture(long id) {
        boolean isRemoved = false;
        try {
            Picture picture = findById(id);
            pictureRepository.delete(picture);
            isRemoved = true;
        } catch (EntityNotFoundException e) {
            //todo
        }
        return isRemoved;
    }

    private Picture findById(Long id) throws EntityNotFoundException {
        return pictureRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    private void validatePictureExists(long pictureId) throws EntityNotFoundException {
        if (!pictureRepository.existsById(pictureId)) {
            throw new EntityNotFoundException(String.valueOf(pictureId));
        }
    }
}
