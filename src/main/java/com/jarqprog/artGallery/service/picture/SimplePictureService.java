package com.jarqprog.artGallery.service.picture;

import com.jarqprog.artGallery.domain.Commentary;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.exception.persistenceException.*;
import com.jarqprog.artGallery.repository.CommentaryRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimplePictureService implements PictureService {

    @Autowired private PictureRepository pictureRepository;
    @Autowired private CommentaryRepository commentaryRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(SimplePictureService.class);

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
        PictureDTO dto;
        long id = pictureDTO.getId();
        try {
            preventCreatingExistingPicture(id);
            Picture picture = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
            Picture saved = pictureRepository.save(picture);
            dto = dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
        } catch (EntityAlreadyExistsException e) {
            throw new CannotCreateEntityException(Picture.class, id, e);
        } catch (Exception ex) {
            throw new CannotCreateEntityException(Picture.class, id, ex.getMessage());
        }
        return dto;
    }

    @Override
    public PictureDTO updatePicture(long id, PictureDTO pictureDTO) {
        PictureDTO dto;
        try {
            validatePictureExists(id);
            pictureDTO.setId(id);
            Picture updated = dtoEntityConverter.convertDtoToEntity(pictureDTO, Picture.class);
            Picture saved = pictureRepository.save(updated);
            dto = dtoEntityConverter.convertEntityToDto(saved, PictureDTO.class);
        } catch (CannotFindEntityException e) {
            throw new CannotUpdateEntityException(Picture.class, id, e);
        } catch (Exception ex) {
            throw new CannotUpdateEntityException(Picture.class, id, ex.getMessage());
        }
        return dto;
    }

    @Override
    public void removePicture(long id) throws EntityNotFoundException {
        try {
            validatePictureExists(id);
            Set<Commentary> commentaries = commentaryRepository.findAllCommentaryByPictureId(id);
            commentaries.forEach(c -> c.setPicture(null));
            commentaryRepository.saveAll(commentaries);
            pictureRepository.deleteById(id);
        } catch (CannotFindEntityException e) {
            throw new CannotRemoveEntityException(Picture.class, id, e);
        } catch (Exception ex) {
            throw new CannotRemoveEntityException(Picture.class, id, ex.getMessage());
        }
    }

    private Picture findById(Long id) {
        return pictureRepository.findById(id).orElseThrow(() -> new CannotFindEntityException(Picture.class, id));
    }

    private void validatePictureExists(long pictureId) {
        if (!pictureRepository.existsById(pictureId)) {
            throw new CannotFindEntityException(Picture.class, pictureId);
        }
    }

    private void preventCreatingExistingPicture(long pictureId) {
        if (pictureRepository.existsById(pictureId)) {
            throw new EntityAlreadyExistsException(Picture.class, pictureId);
        }
    }
}
