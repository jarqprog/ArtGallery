package com.jarqprog.artGallery.domain.useCases.implementation;


import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.exception.ResourceAlreadyExists;
import com.jarqprog.artGallery.domain.exception.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import com.jarqprog.artGallery.springData.repository.PictureRepository;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCommentaryService implements CommentaryService {

    private final CommentaryRepository commentaryRepository;
    private final PictureRepository pictureRepository;
    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public SimpleCommentaryService(CommentaryRepository commentaryRepository,
                                   PictureRepository pictureRepository,
                                   DtoEntityConverter dtoEntityConverter) {
        this.commentaryRepository = commentaryRepository;
        this.pictureRepository = pictureRepository;
        this.dtoEntityConverter = dtoEntityConverter;
    }

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
        long commentaryId = commentaryDTO.getId();
        preventCreatingExistingCommentary(commentaryId);
        Picture picture = findPictureById(pictureId);
        Commentary commentary = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
        commentary.setPicture(picture);
        Commentary created = commentaryRepository.save(commentary);
        return dtoEntityConverter.convertEntityToDto(created, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO updateCommentary(long pictureId, long commentaryId, CommentaryDTO commentaryDTO) {
        validateCommentaryExists(commentaryId);
        Picture picture = findPictureById(pictureId);
        commentaryDTO.setId(commentaryId);
        Commentary updated = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
        updated.setPicture(picture);
        Commentary saved = commentaryRepository.save(updated);
        saved = findById(saved.getId());
        return dtoEntityConverter.convertEntityToDto(saved, CommentaryDTO.class);
    }

    @Override
    public void removeCommentary(long pictureId, long commentaryId) {
        findPictureById(pictureId);
        removeCommentary(commentaryId);
    }

    @Override
    public void removeCommentary(long id) {
        findById(id);
        commentaryRepository.deleteById(id);
    }

    private Commentary findById(long id) {
        return commentaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Commentary.class, id));
    }

    private Picture findPictureById(long pictureId) {
        return pictureRepository
                .findById(pictureId)
                .orElseThrow(() -> new ResourceNotFoundException(Picture.class, pictureId));
    }

    private void validateCommentaryExists(long commentaryId) {
        if (!commentaryRepository.existsById(commentaryId)) {
            throw new ResourceNotFoundException(Commentary.class, commentaryId);
        }
    }

    private void preventCreatingExistingCommentary(long commentaryId) {
        if (commentaryRepository.existsById(commentaryId)) {
            throw new ResourceAlreadyExists(Commentary.class, commentaryId);
        }
    }
}
