package com.jarqprog.artGallery.springData.services;


import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.springData.exceptions.InvalidObjectException;
import com.jarqprog.artGallery.springData.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.components.DtoEntityConverter;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import com.jarqprog.artGallery.springData.repository.PictureRepository;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import com.jarqprog.artGallery.springData.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SimpleCommentaryService implements CommentaryService {

    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final UserRepository userRepository;
    @NonNull private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public SimpleCommentaryService(@NonNull CommentaryRepository commentaryRepository,
                                   @NonNull PictureRepository pictureRepository,
                                   @NonNull UserRepository userRepository,
                                   @NonNull DtoEntityConverter dtoEntityConverter) {
        this.commentaryRepository = commentaryRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
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

        preventCreatingAlreadyExistingCommentary(commentaryId);

        preventPersistingCommentaryWithoutValidPicture(pictureId, commentaryDTO);
        preventPersistingCommentaryWithoutValidUser(commentaryDTO);

        Picture picture = findPictureById(pictureId);
        User user = findUserById(commentaryDTO.getUser().getId());

        Commentary commentary = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
        commentary.setPicture(picture);
        commentary.setUser(user);
        Commentary created = commentaryRepository.save(commentary);
        return dtoEntityConverter.convertEntityToDto(created, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO updateCommentary(long pictureId, long commentaryId, CommentaryDTO commentaryDTO) {
        validateCommentaryExists(commentaryId);

        preventPersistingCommentaryWithoutValidPicture(pictureId, commentaryDTO);
        preventPersistingCommentaryWithoutValidUser(commentaryDTO);

        Picture picture = findPictureById(pictureId);
        User user = findUserById(commentaryDTO.getUser().getId());

        commentaryDTO.setId(commentaryId);

        Commentary updated = dtoEntityConverter.convertDtoToEntity(commentaryDTO, Commentary.class);
        updated.setPicture(picture);
        updated.setUser(user);

        Commentary saved = commentaryRepository.save(updated);
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

    private User findUserById(long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, userId));
    }

    private void validateCommentaryExists(long commentaryId) {
        if (!commentaryRepository.existsById(commentaryId)) {
            throw new ResourceNotFoundException(Commentary.class, commentaryId);
        }
    }

    private void preventCreatingAlreadyExistingCommentary(long commentaryId) {
        if (commentaryRepository.existsById(commentaryId)) {
            throw new ResourceAlreadyExists(Commentary.class, commentaryId);
        }
    }

    private void preventPersistingCommentaryWithoutValidPicture(long pictureId, CommentaryDTO commentaryDTO) {
        PictureDTO pictureDTO = commentaryDTO.getPicture();
        if (pictureDTO == null || pictureDTO.getId() <= 0 || pictureId != pictureDTO.getId()) {
            throw new InvalidObjectException(CommentaryDTO.class, "No valid Picture");
        }
    }

    private void preventPersistingCommentaryWithoutValidUser(CommentaryDTO commentaryDTO) {
        UserDTO userDTO = commentaryDTO.getUser();
        if (userDTO == null || userDTO.getId() <= 0) {
            throw new InvalidObjectException(CommentaryDTO.class, "No valid User");
        }
    }
}
