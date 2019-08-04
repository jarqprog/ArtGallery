package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    @Autowired private PictureRepository pictureRepository;
    @Autowired private EntityMetadataService entityMetadataService;
    @Autowired private DtoEntityConverter dtoEntityConverter;


    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoEntityConverter.convertEntityToDto(p, PictureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findById(long id) throws EntityNotFoundException {
        Picture picture = findPictureById(id);
        return dtoEntityConverter.convertEntityToDto(picture, PictureDTO.class);
    }

    @Override
    public PictureDTO save(PictureDTO pictureDTO) {
        Picture picture = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(picture);
        entityMetadataService.createMetadata(saved);
        return dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    public boolean remove(long id) {
        boolean isRemoved = false;
        try {
            Picture picture = findPictureById(id);
            entityMetadataService.markDiscontinued(picture);
            pictureRepository.delete(picture);
            isRemoved = true;
        } catch (EntityNotFoundException e) {
            //todo
        }
        return isRemoved;
    }

    private Picture findPictureById(Long id) throws EntityNotFoundException {
        return pictureRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }
}
