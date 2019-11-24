package com.jarqprog.artapi.domains.author;

import com.jarqprog.artapi.domains.author.dto.ApiAuthorDTO;
import com.jarqprog.artapi.domains.author.dto.AuthorThin;
import com.jarqprog.artdomain.model.author.Author;
import com.jarqprog.artdomain.model.author.AuthorData;
import com.jarqprog.artdomain.model.author.DomainAuthor;
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
    public List<ApiAuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(a -> dtoConverter.transformEntityTo(a, defaultOutputClass))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends ApiAuthorDTO> List<T> getAllAuthors(Class<T> clazz) {
        return authorRepository.findAll()
                .stream()
                .map(a -> dtoConverter.transformEntityTo(a, clazz))
                .collect(Collectors.toList());
    }

    @Override
    public ApiAuthorDTO findAuthorById(long id) {
        return dtoConverter.transformEntityTo(findById(id), defaultOutputClass);
    }

    @Override
    public <T extends ApiAuthorDTO> T findAuthorById(long id, Class<T> clazz) {
        return dtoConverter.transformEntityTo(findById(id), clazz);
    }

    @Override
    @Transactional
    public long addAuthor(@NonNull final AuthorData authorData) {
        preventCreatingAlreadyExistingAuthor(authorData);

        // validation

        Author author = DomainAuthor
                .createWith()
                .artisticNickname(authorData.getArtisticNickname())
                .contactId(authorData.getContactId())
                .build();

        AuthorEntity saved = authorRepository.save(AuthorEntity.fromAuthor(author));
        logger.info("Author {} created", saved.toString());
        return saved.getId();
    }

    @Override
    @Transactional
    public void updateAuthor(long id, @NonNull AuthorData authorData) {
        validateGivenAuthorIDsAreEqual(id, authorData);
        validateAuthorExists(id);

        // validation

        Author author = DomainAuthor
                .createWith()
                .id(authorData.getId())
                .version(authorData.getVersion())
                .artisticNickname(authorData.getArtisticNickname())
                .contactId(authorData.getContactId())
                .build();

        AuthorEntity saved = authorRepository.save(AuthorEntity.fromAuthor(author));
        logger.info("Author {} updated", saved.toString());
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

    private void preventCreatingAlreadyExistingAuthor(AuthorData authorData) {
        long id = authorData.getId();
        if (id > 0 && authorRepository.existsById(id)) {
            throw new ResourceAlreadyExists(AuthorEntity.class, id);
        }
    }

    private void validateGivenAuthorIDsAreEqual(long authorId, AuthorData authorData) {
        if (authorId != authorData.getId()) {
            throw new IllegalArgumentException("different author's IDs were given");
        }
    }

    private void validateAuthorExists(long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException(AuthorEntity.class, authorId);
        }
    }
}
