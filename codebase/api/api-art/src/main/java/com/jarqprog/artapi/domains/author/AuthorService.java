package com.jarqprog.artapi.domains.author;

import com.jarqprog.artapi.domains.author.dto.ApiAuthorDTO;
import com.jarqprog.artdomain.author.AuthorData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    List<ApiAuthorDTO> getAllAuthors();
    <T extends ApiAuthorDTO> List<T> getAllAuthors(Class<T> clazz);

    ApiAuthorDTO findAuthorById(long id);
    <T extends ApiAuthorDTO> T findAuthorById(long id, Class<T> clazz);

    long addAuthor(@NonNull AuthorData authorData);

    void updateAuthor(long id, @NonNull AuthorData authorData);

    void removeAuthor(long id);
}
