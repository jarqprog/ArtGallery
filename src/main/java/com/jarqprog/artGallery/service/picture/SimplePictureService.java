package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    @Autowired private PictureRepository pictureRepository;
    @Autowired private EntityMetadataService entityMetadataService;
//    @Autowired private UserRepository userRepository;
//    @Autowired private ContactRepository contactRepository;
    @Autowired private ModelMapper modelMapper;


    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(this::convertPictureToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findById(long id) throws EntityNotFoundException {
        Picture picture = findPictureById(id);
        return convertPictureToDto(picture);
    }

    @Override
    public PictureDTO save(PictureDTO pictureDTO) {
        Picture picture = convertPictureDTOToEntity(pictureDTO);
        Picture saved = pictureRepository.save(picture);
        entityMetadataService.createMetadata(saved);
        return convertPictureToDto(saved);
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

    private PictureDTO convertPictureToDto(Picture picture) {
        return modelMapper.map(picture, PictureDTO.class);
    }

    private Picture convertPictureDTOToEntity(PictureDTO pictureDTO) {
        return modelMapper.map(pictureDTO, Picture.class);
    }

    private Picture findPictureById(Long id) throws EntityNotFoundException {
        return pictureRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }
}
