package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.AuthorDto;
import com.openclassrooms.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Secured("ADMIN")
    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll();
    }

    @Secured("ADMIN")
    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @Secured("ADMIN")
    @PostMapping
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createOrUpdate(authorDto);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createOrUpdate(authorDto);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
