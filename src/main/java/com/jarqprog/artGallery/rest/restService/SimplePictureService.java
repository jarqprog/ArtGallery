package com.jarqprog.artGallery.rest.restService;

import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.domain.exception.ResourceAlreadyExists;
import com.jarqprog.artGallery.domain.exception.ResourceNotFoundException;
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

    @Autowired private PictureRepository pictureRepository;
    @Autowired private CommentaryRepository commentaryRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;

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
