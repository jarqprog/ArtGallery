package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SimplePictureService implements PictureService {

    private final PictureRepository pictureRepository;
    private final EntityMetadataService entityMetadataService;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    @Autowired private ModelMapper modelMapper;

    @Autowired
    public SimplePictureService(PictureRepository pictureRepository,
                                EntityMetadataService entityMetadataService,
                                UserRepository userRepository,
                                ContactRepository contactRepository) {
        this.pictureRepository = pictureRepository;
        this.entityMetadataService = entityMetadataService;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(this::convertPictureToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findById(Long id) throws EntityNotFoundException {
        Picture picture = findPictureById(id);
        return convertPictureToDto(picture);
    }

    @Override
    public PictureDTO save(PictureDTO pictureDTO) {
        Picture picture = convertPictureDTOToEntity(pictureDTO);
        pictureRepository.save(picture);
        entityMetadataService.createMetadata(picture);
        return pictureDTO;
    }

    @Override
    public boolean remove(Long id) {
        boolean isRemoved = false;
        try {
            log.info("Removing picture with id: " + id);
            Picture picture = findPictureById(id);
            entityMetadataService.markDiscontinued(picture);
            pictureRepository.delete(picture);
            isRemoved = true;
            log.info("Picture removed");
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
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
