package com.jarqprog.artapi.domains.author.controller;

import com.jarqprog.artapi.domains.author.AuthorService;
import com.jarqprog.artapi.domains.author.dto.ApiAuthorDTO;
import com.jarqprog.artapi.domains.author.dto.AuthorFat;
import com.jarqprog.artapi.domains.author.dto.AuthorThin;
import com.jarqprog.commonapi.constants.ApiConstants;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.commonapi.constants.OutputMode.FAT;


@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "authors")
public class AuthorController {

    @NonNull
    private final AuthorService authorService;

    @Autowired
    public AuthorController(@NonNull AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<? extends ApiAuthorDTO> getAllAuthors(@RequestParam(required = false, name = "mode") String mode) {
        return authorService.getAllAuthors(getOutputClass(mode));
    }

    @GetMapping("/{id}")
    public ApiAuthorDTO findAuthorById(@PathVariable("id") long id,
                                       @RequestParam(required = false, name = "mode") String mode) {
        return authorService.findAuthorById(id, getOutputClass(mode));
    }

    @PostMapping
    public ResponseEntity addAuthor(@RequestBody ApiAuthorDTO authorDTO) {
        long id = authorService.addAuthor(authorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCommentary(@PathVariable("id") long id,
                                           @RequestBody ApiAuthorDTO authorDTO) {
        authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCommentary(@PathVariable("id") long id) {
        authorService.removeAuthor(id);
        return ResponseEntity.accepted().build();
    }

    private Class<? extends ApiAuthorDTO> getOutputClass(String mode) {
        Class<AuthorThin> defaultOutput = AuthorThin.class;
        if (mode == null) {
            return defaultOutput;
        }
        switch (mode) {
            case FAT:
                return AuthorFat.class;
            default:
                return defaultOutput;
        }
    }
}
