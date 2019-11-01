package com.jarqprog.artGallery.api.domains.artistic.author;

import com.jarqprog.artGallery.domain.artistic.AuthorData;
import lombok.NonNull;

import java.util.List;

public interface AuthorService {

    List<AuthorData> getAllAuthors();
    <T extends AuthorData> List<AuthorData> getAllAuthors(Class<T> clazz);

    AuthorData findAuthorById(long id);
    <T extends AuthorData> T findAuthorById(long id, Class<T> clazz);

    long addAuthor(@NonNull AuthorData authorData);

    void updateAuthor(long id, @NonNull AuthorData authorData);

    void removeAuthor(long id);
}
