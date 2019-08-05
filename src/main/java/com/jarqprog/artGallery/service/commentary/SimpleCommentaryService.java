package com.jarqprog.artGallery.service.commentary;


import com.jarqprog.artGallery.domain.Commentary;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.dto.CommentaryDTO;
import com.jarqprog.artGallery.helper.DtoEntityConverter;
import com.jarqprog.artGallery.repository.CommentaryRepository;
import com.jarqprog.artGallery.repository.PictureRepository;
import com.jarqprog.artGallery.service.metadata.EntityMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCommentaryService implements CommentaryService {

    @Autowired private CommentaryRepository commentaryRepository;
    @Autowired private PictureRepository pictureRepository;
    @Autowired private EntityMetadataService entityMetadataService;
    @Autowired private DtoEntityConverter dtoEntityConverter;

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
        return commentaryRepository
                .findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoEntityConverter.convertEntityToDto(c, CommentaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentaryDTO findCommentaryById(long pictureId, long commentaryId) throws EntityNotFoundException {
        findPictureById(pictureId);// throws exception if not founded
        return findCommentaryById(commentaryId);
    }

    //todo add user context to methods

    @Override
    public CommentaryDTO findCommentaryById(long id) throws EntityNotFoundException {
        Commentary commentary = findById(id);
        return dtoEntityConverter.convertEntityToDto(commentary, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO addCommentary(long pictureId, CommentaryDTO commentaryDTO) {
        if (commentaryRepository.existsById(commentaryDTO.getId())) {
            //throw exception
        }
        Picture picture = findPictureById(pictureId);// throws exception if not founded
        Commentary commentary = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
        commentary.setPicture(picture);
        Commentary created = commentaryRepository.save(commentary);
        entityMetadataService.createMetadata(created);
        return dtoEntityConverter.convertEntityToDto(created, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO updateCommentary(long pictureId, long commentaryId, CommentaryDTO commentaryDTO)
            throws EntityNotFoundException {
        validateCommentaryExists(commentaryId);
        Picture picture = findPictureById(pictureId);// throws exception if not founded
        commentaryDTO.setId(commentaryId);
        Commentary updated = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
        updated.setPicture(picture);
        Commentary saved = commentaryRepository.save(updated);
        entityMetadataService.createMetadata(saved);
        return dtoEntityConverter.convertEntityToDto(saved, CommentaryDTO.class);
    }

    @Override
    public boolean removeCommentary(long pictureId, long commentaryId) throws EntityNotFoundException {
        findPictureById(pictureId);// throws exception if not founded
        return removeCommentary(commentaryId);
    }

    @Override
    public boolean removeCommentary(long id) throws EntityNotFoundException {
        Commentary commentary = findById(id);
        entityMetadataService.markDiscontinued(commentary);
        commentaryRepository.deleteById(id);
        return true;
    }

    private Commentary findById(long id) throws EntityNotFoundException {
        return commentaryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    private Picture findPictureById(long pictureId) throws EntityNotFoundException {
        return pictureRepository
                .findById(pictureId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(pictureId)));
    }

    private void validateCommentaryExists(long commentaryId) throws EntityNotFoundException {
        if (!commentaryRepository.existsById(commentaryId)) {
            throw new EntityNotFoundException(String.valueOf(commentaryId));
        }
    }
}