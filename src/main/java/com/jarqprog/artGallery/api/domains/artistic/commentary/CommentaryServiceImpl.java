package com.jarqprog.artGallery.api.domains.artistic.commentary;


import com.jarqprog.artGallery.api.domains.artistic.commentary.validation.CommentaryValidator;
import com.jarqprog.artGallery.domain.artistic.Commentary;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureEntity;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureRepository;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaryServiceImpl implements CommentaryService {

    private static final Logger logger = LoggerFactory.getLogger(CommentaryServiceImpl.class);

    @NonNull private final CommentaryValidator commentaryValidator;
    @NonNull private final CommentaryRepository commentaryRepository;
    @NonNull private final PictureRepository pictureRepository;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public CommentaryServiceImpl(@NonNull CommentaryValidator commentaryValidator,
                                 @NonNull CommentaryRepository commentaryRepository,
                                 @NonNull PictureRepository pictureRepository,
                                 @NonNull DtoConverter dtoConverter) {
        this.commentaryValidator = commentaryValidator;
        this.commentaryRepository = commentaryRepository;
        this.pictureRepository = pictureRepository;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public List<Commentary> getAllCommentaries() {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToModel(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends Commentary> List<T> getAllCommentaries(Class<T> clazz) {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.convertEntityToModel(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public List<Commentary> getAllCommentariesByPicture(long pictureId) {
        return commentaryRepository.findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoConverter.convertEntityToModel(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends Commentary> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz) {
        return commentaryRepository.findAllCommentaryByPictureId(pictureId)
                .stream()
                .map(c -> dtoConverter.convertEntityToModel(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public Commentary findCommentaryById(long id) {
        CommentaryEntity commentary = findById(id);
        return dtoConverter.convertEntityToModel(commentary, CommentaryThin.class);
    }

    @Override
    public <T extends Commentary> T findCommentaryById(long id, Class<T> clazz) {
        CommentaryEntity commentary = findById(id);
        return dtoConverter.convertEntityToModel(commentary, clazz);
    }

    @Override
    @Transactional
    public long addCommentary(long pictureId, @NonNull Commentary commentary) {
        commentaryValidator.validateOnCreation(commentary);

        preventCreatingAlreadyExistingCommentary(commentary);
        validateGivenPictureIDsAreEqual(pictureId, commentary);
        PictureEntity picture = findPictureById(commentary.getPictureId());

        CommentaryEntity commentaryEntity = new CommentaryEntity(commentary.getComment(), picture);
        commentaryEntity.setUserLogin(commentary.getUserLogin());
        CommentaryEntity saved = commentaryRepository.save(commentaryEntity);
        return saved.getId();
    }

    @Override
    @Transactional
    public void updateCommentary(long pictureId, long commentaryId,
                                          @NonNull Commentary commentary) {
        commentaryValidator.validateOnUpdate(commentary);
        validateGivenCommentaryIDsAreEqual(commentaryId, commentary);
        validateCommentaryExists(pictureId, commentaryId);
        CommentaryEntity commentaryEntity = findById(commentaryId);
        commentaryEntity.setVersion(commentary.getVersion());
        commentaryEntity.setComment(commentary.getComment());
        commentaryRepository.save(commentaryEntity);
    }

    @Override
    @Transactional
    public void removeCommentary(long id) {
        commentaryRepository.deleteById(id);
    }

    @Override
    public void validateCommentaryExists(long pictureId, long commentaryId) {
        if (!commentaryRepository.existsByIdAndPictureId(commentaryId, pictureId)) {
            throw new IllegalArgumentException("Given Commentary ID and Picture ID do not match!");
        }
    }

    private CommentaryEntity findById(long id) {
        return commentaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CommentaryEntity.class, id));
    }

    private PictureEntity findPictureById(long pictureId) {
        return pictureRepository
                .findById(pictureId)
                .orElseThrow(() -> new ResourceNotFoundException(PictureEntity.class, pictureId));
    }

    private void preventCreatingAlreadyExistingCommentary(Commentary commentary) {
        long id = commentary.getId();
        if (id > 0 && commentaryRepository.existsById(id)) {
            throw new ResourceAlreadyExists(CommentaryEntity.class, id);
        }
    }

    private void validateGivenPictureIDsAreEqual(long pictureId, Commentary commentary) {
        if (pictureId != commentary.getPictureId()) {
            throw new IllegalArgumentException("different picture's IDs were given");
        }
    }

    private void validateGivenCommentaryIDsAreEqual(long commentaryId, Commentary commentary) {
        if (commentaryId != commentary.getId()) {
            throw new IllegalArgumentException("different commentary's IDs were given");
        }
    }
}
