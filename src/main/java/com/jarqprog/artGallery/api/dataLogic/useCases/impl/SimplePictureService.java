package com.jarqprog.artGallery.api.dataLogic.useCases.impl;

import com.jarqprog.artGallery.api.dataLogic.repositories.AuthorRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.UserRepository;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.thinDTO.PictureThin;
import com.jarqprog.artGallery.domain.entity.Author;
import com.jarqprog.artGallery.domain.entity.Picture;
import com.jarqprog.artGallery.domain.dto.fatDTO.PictureFat;
import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.dataLogic.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.dataLogic.repositories.CommentaryRepository;
import com.jarqprog.artGallery.api.dataLogic.repositories.PictureRepository;

import com.jarqprog.artGallery.api.dataLogic.useCases.PictureService;
import com.jarqprog.artGallery.domain.entity.User;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SimplePictureService implements PictureService {

    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final AuthorRepository authorRepository;
    @NonNull private final UserRepository userRepository;
    @NonNull private final DtoConverter dtoConverter;

    public SimplePictureService(@NonNull PictureRepository pictureRepository,
                                @NonNull CommentaryRepository commentaryRepository,
                                @NonNull AuthorRepository authorRepository,
                                @NonNull UserRepository userRepository,
                                @NonNull DtoConverter dtoConverter) {
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
    }

    @Autowired


    private static final Logger logger = LoggerFactory.getLogger(SimplePictureService.class);

    @Override
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToDTO(p, PictureThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends PictureDTO> List<T> getAllPictures(Class<T> clazz) {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToDTO(p, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findPictureById(long id) {
        Picture picture = findById(id);
        return dtoConverter.convertEntityToDTO(picture, PictureFat.class);
    }

    @Override
    public <T extends PictureDTO> T findPictureById(long id, Class<T> clazz) {
        Picture picture = findById(id);
        return dtoConverter.convertEntityToDTO(picture, clazz);
    }

    @Override
    public PictureDTO addPicture(@NonNull PictureDTO pictureDTO) {
        preventCreatingExistingPicture(pictureDTO.getId());

        // validation

        Picture picture = new Picture();
        updatePictureByDTO(picture, pictureDTO);
        Picture saved = pictureRepository.save(picture);
        return dtoConverter.convertEntityToDTO(saved, PictureFat.class);
    }

    @Override
    public PictureDTO updatePicture(long id, @NonNull PictureDTO pictureDTO) {

        // validation

        Picture picture = findById(id);
        updatePictureByDTO(picture, pictureDTO);
        Picture saved = pictureRepository.save(picture);
        return dtoConverter.convertEntityToDTO(saved, PictureFat.class);
    }

    @Override
    public void removePicture(long id) {
        validatePictureExists(id);
//        Set<Commentary> commentaries = commentaryRepository.findAllCommentaryByPictureId(id);
//        commentaries.forEach(c -> c.setPicture(null));
//        commentaryRepository.saveAll(commentaries);
        pictureRepository.deleteById(id);
    }

    private Author findAuthorById(long authorId) {
        return authorRepository
                .findById(authorId).orElseThrow(() -> new ResourceNotFoundException(Author.class, authorId));
    }

    private User findUserById(long userId) {
        return userRepository
                .findById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class, userId));
    }

    private Picture findById(long id) {
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

    private void updatePictureByDTO(Picture picture, PictureDTO pictureDTO) {
        picture.setVersion(pictureDTO.getVersion());
        picture.setTitle(pictureDTO.getTitle());
        picture.setPath(picture.getPath());

        if (pictureDTO.getUserId() > 0) {
            picture.setUser(findUserById(pictureDTO.getUserId()));
        }

        if (pictureDTO.getAuthorId() > 0) {
            picture.setAuthor(findAuthorById(pictureDTO.getAuthorId()));
        }
    }
}
