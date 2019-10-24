package com.jarqprog.artGallery.api.dataLogic.useCases.impl;

import com.jarqprog.artGallery.domain.dto.lightDto.PictureDTOLight;
import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.heavyDto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.dataLogic.repositories.CommentaryRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.PictureRepository;

import com.jarqprog.artGallery.api.dataLogic.useCases.PictureService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public SimplePictureService(@NonNull PictureRepository pictureRepository,
                                @NonNull CommentaryRepository commentaryRepository,
                                @NonNull DtoConverter dtoConverter) {
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
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
    public PictureDTO addPicture(@NonNull PictureDTOLight pictureDTO) {
        preventCreatingExistingPicture(pictureDTO.getId());
        Picture picture = dtoConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(picture);
        return dtoConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    public PictureDTO updatePicture(long id, @NonNull PictureDTOLight pictureDTO) {
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
