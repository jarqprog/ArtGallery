package com.jarqprog.artGallery.springData.useCases.impl;


import com.jarqprog.artGallery.domain.dto.lightDto.CommentaryDTOLight;
import com.jarqprog.artGallery.domain.entity.Commentary;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.heavyDto.CommentaryDTO;
import com.jarqprog.artGallery.domain.entity.User;
import com.jarqprog.artGallery.springData.components.dtoValidators.CommentaryValidator;
import com.jarqprog.artGallery.springData.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import com.jarqprog.artGallery.springData.repository.PictureRepository;
import com.jarqprog.artGallery.springData.useCases.CommentaryService;
import com.jarqprog.artGallery.springData.repository.UserRepository;
import com.jarqprog.artGallery.springWebMVC.controller.ErrorController;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SimpleCommentaryService implements CommentaryService {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

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
                .map(c -> dtoConverter.convertEntityToDto(c, CommentaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentaryDTO> getAllCommentariesByPicture(long pictureId) {
        return commentaryRepository.findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoConverter.convertEntityToDto(c, CommentaryDTO.class))
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
        return dtoConverter.convertEntityToDto(commentary, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO addCommentary(long pictureId, @NonNull CommentaryDTOLight commentaryDTO) {
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
        return dtoConverter.convertEntityToDto(created, CommentaryDTO.class);
    }

    @Override
    public CommentaryDTO updateCommentary(long pictureId, long commentaryId,
                                          @NonNull CommentaryDTOLight commentaryDTO) {
        commentaryValidator.validateOnUpdate(commentaryDTO);
        validateGivenCommentaryIDsAreEqual(commentaryId, commentaryDTO);
        findPictureById(pictureId);

        Commentary commentary = findById(commentaryId);
        commentary.setComment(commentaryDTO.getComment());

        Commentary saved = commentaryRepository.save(commentary);
        return dtoConverter.convertEntityToDto(saved, CommentaryDTO.class);
    }

    @Override
    public void removeCommentary(long pictureId, long commentaryId) {
        findPictureById(pictureId);
        Commentary commentary = findById(commentaryId);

        if (pictureId != commentary.getPicture().getId()) {
            throw new IllegalArgumentException("Incorrect Picture ID");
        }
        commentaryRepository.deleteById(commentaryId);
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

    private void preventCreatingAlreadyExistingCommentary(CommentaryDTOLight commentaryDTO) {
        long id = commentaryDTO.getId();
        if (id > 0 && commentaryRepository.existsById(id)) {
            throw new ResourceAlreadyExists(Commentary.class, id);
        }
    }

    private void validateGivenPictureIDsAreEqual(long pictureId, CommentaryDTOLight commentaryDTO) {
        if (pictureId != commentaryDTO.getPictureId()) {
            throw new IllegalArgumentException("different picture's IDs were given");
        }
    }

    private void validateGivenCommentaryIDsAreEqual(long commentaryId, CommentaryDTOLight commentaryDTO) {
        if (commentaryId != commentaryDTO.getId()) {
            throw new IllegalArgumentException("different commentary's IDs were given");
        }
    }
}
