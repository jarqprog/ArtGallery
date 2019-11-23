package com.jarqprog.artGallery.api.domains.artistic.author.controller;

import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.domains.artistic.author.AuthorService;
import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorDTO;
import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorFat;
import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorThin;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.artGallery.api.OutputMode.FAT;


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
    public List<? extends AuthorDTO> getAllAuthors(@RequestParam(required = false, name = "mode") String mode) {
        return authorService.getAllAuthors(getOutputClass(mode));
    }

    @GetMapping("/{id}")
    public AuthorDTO findAuthorById(@PathVariable("id") long id,
                                     @RequestParam(required = false, name = "mode") String mode) {
        return authorService.findAuthorById(id, getOutputClass(mode));
    }

    @PostMapping
    public ResponseEntity addAuthor(@RequestBody AuthorDTO authorDTO) {
        long id = authorService.addAuthor(authorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCommentary(@PathVariable("id") long id,
                                           @RequestBody AuthorDTO authorDTO) {
        authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCommentary(@PathVariable("id") long id) {
        authorService.removeAuthor(id);
        return ResponseEntity.accepted().build();
    }

    private Class<? extends AuthorDTO> getOutputClass(String mode) {
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
