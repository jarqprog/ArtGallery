package com.jarqprog.artGallery.api.domains.artistic.author;

import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorThin;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.DtoConverter;
import com.jarqprog.artGallery.domain.artistic.Author;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final Class<AuthorThin> defaultOutputClass = AuthorThin.class;

    @NonNull private final AuthorRepository authorRepository;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public AuthorServiceImpl(@NonNull AuthorRepository authorRepository,
                             @NonNull DtoConverter dtoConverter) {
        this.authorRepository = authorRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(a -> dtoConverter.convertEntityToModel(a, defaultOutputClass))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends Author> List<Author> getAllAuthors(Class<T> clazz) {
        return authorRepository.findAll()
                .stream()
                .map(a -> dtoConverter.convertEntityToModel(a, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public Author findAuthorById(long id) {
        return dtoConverter.convertEntityToModel(findById(id), defaultOutputClass);
    }

    @Override
    public <T extends Author> T findAuthorById(long id, Class<T> clazz) {
        return dtoConverter.convertEntityToModel(findById(id), clazz);
    }

    @Override
    @Transactional
    public long addAuthor(@NonNull final Author author) {
        preventCreatingAlreadyExistingAuthor(author);

        // validation

        AuthorEntity authorEntity = new AuthorEntity(author.getArtisticNickname(), author.getContactId());
        AuthorEntity saved = authorRepository.save(authorEntity);
        logger.info("Author {} created", saved.toString());
        return saved.getId();
    }

    @Override
    @Transactional
    public void updateAuthor(long id, @NonNull Author author) {
        validateGivenAuthorIDsAreEqual(id, author);
        validateAuthorExists(id);

        // validation

        AuthorEntity authorEntity = findById(id);
        authorEntity.setArtisticNickname(author.getArtisticNickname());
        authorEntity.setContactId(author.getContactId());
        authorRepository.save(authorEntity);
        logger.info("Author {} updated", authorEntity.toString());
    }

    @Override
    @Transactional
    public void removeAuthor(long id) {
        validateAuthorExists(id);
        authorRepository.deleteById(id);
        logger.info("Author with ID={} deleted", id);
    }

    private AuthorEntity findById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AuthorEntity.class, id));
    }

    private void preventCreatingAlreadyExistingAuthor(Author author) {
        long id = author.getId();
        if (id > 0 && authorRepository.existsById(id)) {
            throw new ResourceAlreadyExists(AuthorEntity.class, id);
        }
    }

    private void validateGivenAuthorIDsAreEqual(long authorId, Author author) {
        if (authorId != author.getId()) {
            throw new IllegalArgumentException("different author's IDs were given");
        }
    }

    private void validateAuthorExists(long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException(AuthorEntity.class, authorId);
        }
    }
}
