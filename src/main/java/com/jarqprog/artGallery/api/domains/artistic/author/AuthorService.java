package com.jarqprog.artGallery.api.domains.artistic.author;

import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorDTO;
import com.jarqprog.artGallery.domain.artistic.AuthorData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    List<AuthorDTO> getAllAuthors();
    <T extends AuthorDTO> List<T> getAllAuthors(Class<T> clazz);

    AuthorDTO findAuthorById(long id);
    <T extends AuthorDTO> T findAuthorById(long id, Class<T> clazz);

    long addAuthor(@NonNull AuthorData authorData);

    void updateAuthor(long id, @NonNull AuthorData authorData);

    void removeAuthor(long id);
}
