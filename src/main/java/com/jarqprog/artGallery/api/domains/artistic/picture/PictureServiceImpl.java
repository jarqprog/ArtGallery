package com.jarqprog.artGallery.api.domains.artistic.picture;

import com.jarqprog.artGallery.api.domains.artistic.author.AuthorRepository;
import com.jarqprog.artGallery.api.domains.artistic.picture.validation.PictureValidator;
import com.jarqprog.artGallery.domain.artistic.Picture;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureThin;
import com.jarqprog.artGallery.api.domains.artistic.author.AuthorEntity;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryRepository;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final AuthorRepository authorRepository;
    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final PictureValidator pictureValidator;

    @Autowired
    public PictureServiceImpl(@NonNull PictureRepository pictureRepository,
                              @NonNull CommentaryRepository commentaryRepository,
                              @NonNull AuthorRepository authorRepository,
                              @NonNull DtoConverter dtoConverter,
                              @NonNull PictureValidator pictureValidator) {
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
        this.authorRepository = authorRepository;
        this.dtoConverter = dtoConverter;
        this.pictureValidator = pictureValidator;
    }

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToModel(p, PictureThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends Picture> List<T> getAllPictures(Class<T> clazz) {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.convertEntityToModel(p, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public Picture findPictureById(long id) {
        PictureEntity picture = findById(id);
        return dtoConverter.convertEntityToModel(picture, PictureThin.class);
    }

    @Override
    public <T extends Picture> T findPictureById(long id, Class<T> clazz) {
        PictureEntity picture = findById(id);
        return dtoConverter.convertEntityToModel(picture, clazz);
    }

    @Override
    @Transactional
    public long addPicture(@NonNull Picture picture) {
        preventCreatingExistingPicture(picture.getId());

        pictureValidator.validateOnCreation(picture);
        PictureEntity pictureEntity = new PictureEntity();
        updatePictureByDTO(pictureEntity, picture);
        pictureEntity.setUserLogin(picture.getUserLogin());

        PictureEntity saved = pictureRepository.save(pictureEntity);
        return saved.getId();
    }

    @Override
    @Transactional
    public void updatePicture(long id, @NonNull Picture pictureDTO) {

        if (id != pictureDTO.getId()) {
            throw new IllegalArgumentException("different picture's IDs were given");
        }
        pictureValidator.validateOnUpdate(pictureDTO);
        PictureEntity picture = findById(id);
        updatePictureByDTO(picture, pictureDTO);
        pictureRepository.save(picture);
    }

    @Override
    @Transactional
    public void removePicture(long id) {
        validatePictureExists(id);
        commentaryRepository.deleteByPictureId(id);
        pictureRepository.deleteById(id);
    }

    private AuthorEntity findAuthorById(long authorId) {
        return authorRepository
                .findById(authorId).orElseThrow(() -> new ResourceNotFoundException(AuthorEntity.class, authorId));
    }

    private PictureEntity findById(long id) {
        return pictureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PictureEntity.class, id));
    }

    private void validatePictureExists(long pictureId) {
        if (!pictureRepository.existsById(pictureId)) {
            throw new ResourceNotFoundException(PictureEntity.class, pictureId);
        }
    }

    private void preventCreatingExistingPicture(long pictureId) {
        if (pictureRepository.existsById(pictureId)) {
            throw new ResourceAlreadyExists(PictureEntity.class, pictureId);
        }
    }

    private void updatePictureByDTO(PictureEntity pictureEntity, Picture picture) {
        pictureEntity.setVersion(picture.getVersion());
        pictureEntity.setTitle(picture.getTitle());
        pictureEntity.setPath(picture.getPath());

        if (pictureValidator.isAuthorIdValid(picture)) {
            AuthorEntity authorEntity = findAuthorById(picture.getAuthorId());
            pictureEntity.setAuthor(authorEntity);
        }
    }
}
