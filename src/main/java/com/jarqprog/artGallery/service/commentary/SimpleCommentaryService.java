package com.jarqprog.artGallery.service.commentary;


import com.jarqprog.artGallery.domain.Commentary;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.CommentaryDTO;
import com.jarqprog.artGallery.exception.persistenceException.*;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.CommentaryRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCommentaryService implements CommentaryService {

    @Autowired private CommentaryRepository commentaryRepository;
    @Autowired private PictureRepository pictureRepository;
    @Autowired private DtoEntityConverter dtoEntityConverter;


    //todo add user context to methods

    @Override
    public List<CommentaryDTO> getAllCommentaries() {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoEntityConverter.convertEntityToDto(c, CommentaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentaryDTO> getAllCommentariesByPicture(long pictureId) {
        return commentaryRepository.findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoEntityConverter.convertEntityToDto(c, CommentaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentaryDTO findCommentaryById(long pictureId, long commentaryId) {
        findPictureById(pictureId);
        return findCommentaryById(commentaryId);
    }

    @Override
    public CommentaryDTO findCommentaryById(long id) {
        Commentary commentary = findById(id);
        return dtoEntityConverter.convertEntityToDto(commentary, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO addCommentary(long pictureId, CommentaryDTO commentaryDTO) {
        CommentaryDTO dto;
        long commentaryId = commentaryDTO.getId();
        try {
            preventCreatingExistingCommentary(commentaryId);
            Picture picture = findPictureById(pictureId);
            Commentary commentary = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
            commentary.setPicture(picture);
            Commentary created = commentaryRepository.save(commentary);
            dto = dtoEntityConverter.convertEntityToDto(created, CommentaryDTO.class);
        } catch (EntityAlreadyExistsException e) {
            throw new CannotCreateEntityException(Commentary.class, commentaryId, e);
        } catch (Exception ex) {
            throw new CannotCreateEntityException(Commentary.class, commentaryId, ex.getMessage());
        }
        return dto;
    }

    @Override
    public CommentaryDTO updateCommentary(long pictureId, long commentaryId, CommentaryDTO commentaryDTO) {
        CommentaryDTO dto;
        try {
            validateCommentaryExists(commentaryId);
            Picture picture = findPictureById(pictureId);
            commentaryDTO.setId(commentaryId);
            Commentary updated = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
            updated.setPicture(picture);
            Commentary saved = commentaryRepository.save(updated);
            dto = dtoEntityConverter.convertEntityToDto(saved, CommentaryDTO.class);
        } catch (CannotFindEntityException e) {
            throw new CannotCreateEntityException(Commentary.class, commentaryId, e);
        } catch (Exception ex) {
            throw new CannotCreateEntityException(Commentary.class, commentaryId, ex.getMessage());
        }
        return dto;
    }

    @Override
    public void removeCommentary(long pictureId, long commentaryId) {
        try {
            findPictureById(pictureId);
        } catch (CannotFindEntityException e) {
            throw new CannotRemoveEntityException(Commentary.class, pictureId, e);
        }
        removeCommentary(commentaryId);
    }

    @Override
    public void removeCommentary(long id) {
        try {
            findById(id);
            commentaryRepository.deleteById(id);
        } catch (CannotFindEntityException e) {
            throw new CannotRemoveEntityException(Commentary.class, id, e);
        } catch (Exception ex) {
            throw new CannotRemoveEntityException(Commentary.class, id, ex.getMessage());
        }
    }

    private Commentary findById(long id) {
        return commentaryRepository.findById(id).orElseThrow(() -> new CannotFindEntityException(Commentary.class, id));
    }

    private Picture findPictureById(long pictureId) {
        return pictureRepository
                .findById(pictureId)
                .orElseThrow(() -> new CannotFindEntityException(Picture.class, pictureId));
    }

    private void validateCommentaryExists(long commentaryId) {
        if (!commentaryRepository.existsById(commentaryId)) {
            throw new CannotFindEntityException(Commentary.class, commentaryId);
        }
    }

    private void preventCreatingExistingCommentary(long commentaryId) {
        if (commentaryRepository.existsById(commentaryId)) {
            throw new EntityAlreadyExistsException(Commentary.class, commentaryId);
        }
    }
}
