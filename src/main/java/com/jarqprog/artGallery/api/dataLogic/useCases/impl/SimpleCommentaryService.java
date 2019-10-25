package com.jarqprog.artGallery.api.dataLogic.useCases.impl;


import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.dto.thinDTO.CommentaryThin;
import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.fatDTO.CommentaryFat;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.api.dataLogic.components.dtoValidators.CommentaryValidator;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.repositories.CommentaryRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.PictureRepository;
import com.jarqprog.artGallery.api.dataLogic.useCases.CommentaryService;
import com.jarqprog.artGallery.api.dataLogic.repositories.UserRepository;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCommentaryService implements CommentaryService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCommentaryService.class);

    @NonNull private final CommentaryValidator commentaryValidator;
    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final UserRepository userRepository;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public SimpleCommentaryService(@NonNull CommentaryValidator commentaryValidator,
                                   @NonNull CommentaryRepository commentaryRepository,
                                   @NonNull PictureRepository pictureRepository,
                                   @NonNull UserRepository userRepository,
                                   @NonNull DtoConverter dtoConverter) {
        this.commentaryValidator = commentaryValidator;
        this.commentaryRepository = commentaryRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public List<CommentaryDTO> getAllCommentaries() {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToDTO(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends CommentaryDTO> List<T> getAllCommentaries(Class<T> clazz) {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToDTO(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentaryDTO> getAllCommentariesByPicture(long pictureId) {
        return commentaryRepository.findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoConverter.convertEntityToDTO(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends CommentaryDTO> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz) {
        return commentaryRepository.findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoConverter.convertEntityToDTO(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public CommentaryDTO findCommentaryById(long id) {
        Commentary commentary = findById(id);
        return dtoConverter.convertEntityToDTO(commentary, CommentaryThin.class);
    }

    @Override
    public <T extends CommentaryDTO> T findCommentaryById(long id, Class<T> clazz) {
        Commentary commentary = findById(id);
        return dtoConverter.convertEntityToDTO(commentary, clazz);
    }

    @Override
    @Transactional
    public CommentaryDTO addCommentary(long pictureId, @NonNull CommentaryDTO commentaryDTO) {
        commentaryValidator.validateOnCreation(commentaryDTO);

        preventCreatingAlreadyExistingCommentary(commentaryDTO);
        validateGivenPictureIDsAreEqual(pictureId, commentaryDTO);

        Picture picture = findPictureById(commentaryDTO.getPictureId());
        User user = findUserById(commentaryDTO.getUserId());

        Commentary commentary = new Commentary();
        commentary.setComment(commentaryDTO.getComment());
        commentary.setPicture(picture);
        commentary.setUser(user);

        Commentary created = commentaryRepository.save(commentary);
        return dtoConverter.convertEntityToDTO(created, CommentaryFat.class);
    }

    @Override
    @Transactional
    public CommentaryDTO updateCommentary(long pictureId, long commentaryId,
                                          @NonNull CommentaryDTO commentaryDTO) {
        commentaryValidator.validateOnUpdate(commentaryDTO);
        validateGivenCommentaryIDsAreEqual(commentaryId, commentaryDTO);
        findPictureById(pictureId);

        Commentary commentary = findById(commentaryId);
        commentary.setComment(commentaryDTO.getComment());

        Commentary saved = commentaryRepository.save(commentary);
        return dtoConverter.convertEntityToDTO(saved, CommentaryFat.class);
    }

    @Override
    @Transactional
    public void removeCommentary(long id) {
        findById(id);
        commentaryRepository.deleteById(id);
    }

    @Override
    public void validateCommentaryExists(long pictureId, long commentaryId) {
        if (!commentaryRepository.existsByIdAndPictureId(commentaryId, pictureId)) {
            throw new IllegalArgumentException("Given Commentary ID and Picture ID do not match!");
        }
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

    private void preventCreatingAlreadyExistingCommentary(CommentaryDTO commentaryDTO) {
        long id = commentaryDTO.getId();
        if (id > 0 && commentaryRepository.existsById(id)) {
            throw new ResourceAlreadyExists(Commentary.class, id);
        }
    }

    private void validateGivenPictureIDsAreEqual(long pictureId, CommentaryDTO commentaryDTO) {
        if (pictureId != commentaryDTO.getPictureId()) {
            throw new IllegalArgumentException("different picture's IDs were given");
        }
    }

    private void validateGivenCommentaryIDsAreEqual(long commentaryId, CommentaryDTO commentaryDTO) {
        if (commentaryId != commentaryDTO.getId()) {
            throw new IllegalArgumentException("different commentary's IDs were given");
        }
    }
}
