package com.jarqprog.artGallery.api.domains.artistic.commentary;


import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.domains.artistic.commentary.validation.CommentaryValidator;
import com.jarqprog.artGallery.domain.artistic.CommentaryData;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureEntity;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureRepository;
import com.jarqprog.artGallery.domain.artistic.DomainCommentary;
import com.jarqprog.artGallery.domain.artistic.Commentary;
import com.jarqprog.artGallery.domain.artistic.Picture;
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
    public List<CommentaryDTO> getAllCommentaries() {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends CommentaryDTO> List<T> getAllCommentaries(Class<T> clazz) {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentaryDTO> getAllCommentariesByPicture(long pictureId) {
        return commentaryRepository.findAllCommentaryByPictureEntityId(pictureId)
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends CommentaryDTO> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz) {
        return commentaryRepository.findAllCommentaryByPictureEntityId(pictureId)
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public CommentaryDTO findCommentaryById(long id) {
        CommentaryEntity commentary = findById(id);
        return dtoConverter.transformEntityTo(commentary, CommentaryThin.class);
    }

    @Override
    public <T extends CommentaryDTO> T findCommentaryById(long id, Class<T> clazz) {
        CommentaryEntity commentary = findById(id);
        return dtoConverter.transformEntityTo(commentary, clazz);
    }

    @Override
    @Transactional
    public long addCommentary(long pictureId, @NonNull CommentaryData commentaryData) {
        commentaryValidator.validateOnCreation(commentaryData);

        preventCreatingAlreadyExistingCommentary(commentaryData);
        validateGivenPictureIDsAreEqual(pictureId, commentaryData);
        Picture picture = findPictureById(commentaryData.getPictureId());

        Commentary commentary = DomainCommentary.createWith()
                .comment(commentaryData.getComment())
                .picture(picture)
                .userLogin(commentaryData.getUserLogin())
                .build();

        CommentaryEntity saved = commentaryRepository.save(CommentaryEntity.fromCommentary(commentary));
        return saved.getId();
    }

    @Override
    @Transactional
    public void updateCommentary(long pictureId, long commentaryId,
                                          @NonNull CommentaryData commentaryData) {
        commentaryValidator.validateOnUpdate(commentaryData);
        validateGivenCommentaryIDsAreEqual(commentaryId, commentaryData);
        validateCommentaryExists(pictureId, commentaryId);

        Picture picture = findPictureById(commentaryData.getPictureId());

        Commentary commentary = DomainCommentary.createWith()
                .id(commentaryData.getId())
                .version(commentaryData.getVersion())
                .comment(commentaryData.getComment())
                .picture(picture)
                .userLogin(commentaryData.getUserLogin())
                .build();

        CommentaryEntity saved = commentaryRepository.save(CommentaryEntity.fromCommentary(commentary));
        commentaryRepository.save(saved);
    }

    @Override
    @Transactional
    public void removeCommentary(long id) {
        commentaryRepository.deleteById(id);
    }

    @Override
    public void validateCommentaryExists(long pictureId, long commentaryId) {
        if (!commentaryRepository.existsByIdAndPictureEntityId(commentaryId, pictureId)) {
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

    private void preventCreatingAlreadyExistingCommentary(CommentaryData commentaryData) {
        long id = commentaryData.getId();
        if (id > 0 && commentaryRepository.existsById(id)) {
            throw new ResourceAlreadyExists(CommentaryEntity.class, id);
        }
    }

    private void validateGivenPictureIDsAreEqual(long pictureId, CommentaryData commentaryData) {
        if (pictureId != commentaryData.getPictureId()) {
            throw new IllegalArgumentException("different picture's IDs were given");
        }
    }

    private void validateGivenCommentaryIDsAreEqual(long commentaryId, CommentaryData commentaryData) {
        if (commentaryId != commentaryData.getId()) {
            throw new IllegalArgumentException("different commentary's IDs were given");
        }
    }
}
