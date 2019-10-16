package com.jarqprog.artGallery.springData.services;

import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.springData.components.DtoEntityConverter;
import com.jarqprog.artGallery.springData.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import com.jarqprog.artGallery.springData.repository.PictureRepository;

import com.jarqprog.artGallery.springData.repository.UserRepository;
import com.jarqprog.artGallery.domain.useCases.PictureService;
import org.apache.log4j.Logger;
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
    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public SimplePictureService(PictureRepository pictureRepository,
                                CommentaryRepository commentaryRepository,
                                UserRepository userRepository,
                                DtoEntityConverter dtoEntityConverter) {
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
        this.userRepository = userRepository;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    private static final Logger logger = Logger.getLogger(SimplePictureService.class);

    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoEntityConverter.convertEntityToDto(p, PictureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findPictureById(long id) {
        Picture picture = findById(id);
        return dtoEntityConverter.convertEntityToDto(picture, PictureDTO.class);
    }

    @Override
    public PictureDTO addPicture(PictureDTO pictureDTO) {
        preventCreatingExistingPicture(pictureDTO.getId());
        Picture picture = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(picture);
        return dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
    }

    @Override
    public PictureDTO updatePicture(long id, PictureDTO pictureDTO) {
        validatePictureExists(id);
        pictureDTO.setId(id);
        Picture updated = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
        Picture saved = pictureRepository.save(updated);
        return dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
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
