package com.jarqprog.artapi.domains.commentary;


import com.jarqprog.artapi.domains.commentary.dto.ApiCommentaryDTO;
import com.jarqprog.artapi.domains.commentary.dto.CommentaryThin;
import com.jarqprog.artapi.domains.commentary.validation.CommentaryValidator;
import com.jarqprog.artapi.domains.picture.PictureEntity;
import com.jarqprog.artapi.domains.picture.PictureRepository;
import com.jarqprog.artdomain.model.commentary.Commentary;
import com.jarqprog.artdomain.model.commentary.CommentaryData;
import com.jarqprog.artdomain.model.commentary.DomainCommentary;
import com.jarqprog.artdomain.model.picture.Picture;
import com.jarqprog.commonapi.components.DtoConverter;
import com.jarqprog.commonapi.exceptions.ResourceAlreadyExists;
import com.jarqprog.commonapi.exceptions.ResourceNotFoundException;
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
    public List<ApiCommentaryDTO> getAllCommentaries() {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends ApiCommentaryDTO> List<T> getAllCommentaries(Class<T> clazz) {
        return commentaryRepository
                .findAll()
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiCommentaryDTO> getAllCommentariesByPicture(long pictureId) {
        return commentaryRepository.findAllCommentaryByPictureEntityId(pictureId)
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, CommentaryThin.class))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends ApiCommentaryDTO> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz) {
        return commentaryRepository.findAllCommentaryByPictureEntityId(pictureId)
                .stream()
                .map(c -> dtoConverter.transformEntityTo(c, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public ApiCommentaryDTO findCommentaryById(long id) {
        com.jarqprog.artapi.domains.commentary.CommentaryEntity commentary = findById(id);
        return dtoConverter.transformEntityTo(commentary, CommentaryThin.class);
    }

    @Override
    public <T extends ApiCommentaryDTO> T findCommentaryById(long id, Class<T> clazz) {
        com.jarqprog.artapi.domains.commentary.CommentaryEntity commentary = findById(id);
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

        com.jarqprog.artapi.domains.commentary.CommentaryEntity saved = commentaryRepository.save(com.jarqprog.artapi.domains.commentary.CommentaryEntity.fromCommentary(commentary));
        return saved.getId();
    }

    @Override
    @Transactional
    public void updateCommentary(long pictureId, long commentaryId,
                                          @NonNull CommentaryData commentaryData) {
        commentaryValidator.validateOnUpdate(commentaryData);
        validateGivenCommentaryIDsAreEqual(commentaryId, commentaryData);
        validateGivenPictureIDsAreEqual(pictureId, commentaryData);
        validatePictureIdAndUserLoginOnUpdate(commentaryData);

        Picture picture = findPictureById(commentaryData.getPictureId());

        Commentary commentary = DomainCommentary.createWith()
                .id(commentaryData.getId())
                .version(commentaryData.getVersion())
                .comment(commentaryData.getComment())
                .picture(picture)
                .userLogin(commentaryData.getUserLogin())
                .build();

        commentaryRepository.save(com.jarqprog.artapi.domains.commentary.CommentaryEntity.fromCommentary(commentary));
    }

    @Override
    @Transactional
    public void removeCommentary(long id) {
        commentaryRepository.deleteById(id);
    }

    @Override
    public void validateCommentaryExists(long pictureId, long commentaryId) {
        if (!commentaryRepository.existsByIdAndPictureEntityId(commentaryId, pictureId)) {
            throw new ResourceNotFoundException(com.jarqprog.artapi.domains.commentary.CommentaryEntity.class, commentaryId);
        }
    }

    private com.jarqprog.artapi.domains.commentary.CommentaryEntity findById(long id) {
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

    private void validatePictureIdAndUserLoginOnUpdate(CommentaryData commentaryData) {
        final long commentaryId = commentaryData.getId();
        final long pictureId = commentaryData.getPictureId();
        final String userLogin = commentaryData.getUserLogin();
        if (!commentaryRepository.existsByIdAndPictureEntityIdAndUserLogin(commentaryId, pictureId, userLogin)) {
            throw new IllegalArgumentException(String.format("commentary ID=%s not found by picture ID=%s" +
                    "or user login=%s. Picture or user login update on commentary - forbidden",
                    commentaryId, pictureId, userLogin));
        }
    }

    private void validateGivenCommentaryIDsAreEqual(long commentaryId, CommentaryData commentaryData) {
        if (commentaryId != commentaryData.getId()) {
            throw new IllegalArgumentException("different commentary's IDs were given");
        }
    }
}
