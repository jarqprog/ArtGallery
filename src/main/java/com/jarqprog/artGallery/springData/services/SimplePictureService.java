package com.jarqprog.artGallery.springData.services;

import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.heavyDto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.springData.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import com.jarqprog.artGallery.springData.repository.PictureRepository;

import com.jarqprog.artGallery.springData.repository.UserRepository;
import com.jarqprog.artGallery.domain.useCases.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    private final PictureRepository pictureRepository;
    private final CommentaryRepository commentaryRepository;
    private final UserRepository userRepository;
    private final DtoConverter dtoConverter;

    @Autowired
    public SimplePictureService(PictureRepository pictureRepository,
                                CommentaryRepository commentaryRepository,
                                UserRepository userRepository,
                                DtoConverter dtoConverter) {
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
    }

    private static final Logger logger = LoggerFactory.getLogger(SimplePictureService.class);

    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToDto(p, PictureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findPictureById(long id) {
        Picture picture = findById(id);
        return dtoConverter.convertEntityToDto(picture, PictureDTO.class);
    }

    @Override
    public PictureDTO addPicture(PictureDTO pictureDTO) {
        preventCreatingExistingPicture(pictureDTO.getId());
        Picture picture = dtoConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(picture);
        return dtoConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    public PictureDTO updatePicture(long id, PictureDTO pictureDTO) {
        validatePictureExists(id);
        pictureDTO.setId(id);
        Picture updated = dtoConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(updated);
        return dtoConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    @Transactional
    public void removePicture(long id) {
        validatePictureExists(id);
        Set<Commentary> commentaries = commentaryRepository.findAllCommentaryByPictureId(id);
        commentaries.forEach(c -> c.setPicture(null));
        commentaryRepository.saveAll(commentaries);
        pictureRepository.deleteById(id);
    }

    private Picture findById(Long id) {
        return pictureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Picture.class, id));
    }

    private void validatePictureExists(long pictureId) {
        if (!pictureRepository.existsById(pictureId)) {
            throw new ResourceNotFoundException(Picture.class, pictureId);
        }
    }

    private void preventCreatingExistingPicture(long pictureId) {
        if (pictureRepository.existsById(pictureId)) {
            throw new ResourceAlreadyExists(Picture.class, pictureId);
        }
    }
}
