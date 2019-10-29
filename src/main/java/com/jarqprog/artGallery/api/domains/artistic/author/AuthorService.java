package com.jarqprog.artGallery.api.domains.artistic.author;

import com.jarqprog.artGallery.domain.artistic.Author;
import lombok.NonNull;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();
    <T extends Author> List<Author> getAllAuthors(Class<T> clazz);

    Author findAuthorById(long id);
    <T extends Author> T findAuthorById(long id, Class<T> clazz);

    long addAuthor(@NonNull Author author);

    void updateAuthor(long id, @NonNull Author author);

    void removeAuthor(long id);
}
