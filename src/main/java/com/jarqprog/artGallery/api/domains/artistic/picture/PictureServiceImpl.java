package com.jarqprog.artGallery.api.domains.artistic.picture;

import com.jarqprog.artGallery.api.domains.artistic.author.AuthorRepository;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureDTO;
import com.jarqprog.artGallery.api.domains.artistic.picture.validation.PictureValidator;
import com.jarqprog.artGallery.domain.artistic.DomainPicture;
import com.jarqprog.artGallery.domain.artistic.Picture;
import com.jarqprog.artGallery.domain.artistic.PictureData;
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
    public List<PictureDTO> getAllPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.transformEntityTo(p, PictureThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends PictureDTO> List<T> getAllPictures(Class<T> clazz) {
        return pictureRepository.findAll()
                .stream()
                .map(p -> dtoConverter.transformEntityTo(p, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public PictureDTO findPictureById(long id) {
        PictureEntity picture = findById(id);
        return dtoConverter.transformEntityTo(picture, PictureThin.class);
    }

    @Override
    public <T extends PictureDTO> T findPictureById(long id, Class<T> clazz) {
        PictureEntity picture = findById(id);
        return dtoConverter.transformEntityTo(picture, clazz);
    }

    @Override
    @Transactional
    public long addPicture(@NonNull PictureData pictureData) {
        preventCreatingExistingPicture(pictureData.getId());

        pictureValidator.validateOnCreation(pictureData);

        Picture picture = DomainPicture.createWith()
                .path(pictureData.getPath())
                .title(pictureData.getTitle())
                .userLogin(pictureData.getUserLogin())
                .author(pictureData.hasAuthor() ? findAuthorById(pictureData.getAuthorId()) : null)
                .build();

        PictureEntity saved = pictureRepository.save(PictureEntity.fromPicture(picture));
        return saved.getId();
    }

    @Override
    @Transactional
    public void updatePicture(long id, @NonNull PictureData pictureData) {

        if (id != pictureData.getId()) {
            throw new IllegalArgumentException("different picture's IDs were given");
        }
        pictureValidator.validateOnUpdate(pictureData);

        Picture picture = DomainPicture.createWith()
                .id(pictureData.getId())
                .version(pictureData.getVersion())
                .path(pictureData.getPath())
                .title(pictureData.getTitle())
                .userLogin(pictureData.getUserLogin())
                .author(pictureData.hasAuthor() ? findAuthorById(pictureData.getAuthorId()) : null)
                .build();

        pictureRepository.save(PictureEntity.fromPicture(picture));
    }

    @Override
    @Transactional
    public void removePicture(long id) {
        validatePictureExists(id);
        commentaryRepository.deleteByPictureEntityId(id);
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
}
