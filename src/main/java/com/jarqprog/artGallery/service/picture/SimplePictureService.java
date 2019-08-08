package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Commentary;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.exception.persistenceException.*;
import com.jarqprog.artGallery.repository.CommentaryRepository;
import com.jarqprog.artGallery.repository.PictureRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    @Autowired private PictureRepository pictureRepository;
    @Autowired private CommentaryRepository commentaryRepository;
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
