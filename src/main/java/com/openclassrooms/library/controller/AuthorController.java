package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.AuthorDto;
import com.openclassrooms.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle Authors
 *
 * @see com.openclassrooms.library.entity.Author
 * @see AuthorDto
 * @see AuthorService
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * Method that returns a list of authors
     * Only an admin can access this resource
     *
     * @return a list of authors
     * @see AuthorService#findAll()
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll();
    }

    /**
     * Method to get an author
     * Only an admin cas access this resource
     *
     * @param id id of the author searched
     *
     * @return an author
     * @see AuthorService#findById(Long)
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable Long id) {
        return authorService.findById(id);
    }

    /**
     * Method to create an author
     * Only an admin can access this resource
     *
     * @param authorDto the author to save
     *
     * @return the saved author
     * @see AuthorService#createOrUpdate(AuthorDto)
     */
    @Secured("ROLE_ADMIN")
    @PostMapping
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createOrUpdate(authorDto);
    }

    /**
     * Method to update an author
     * Only an admin can access this resource
     *
     * @param authorDto the author to save
     *
     * @return the updated author
     * @see AuthorService#createOrUpdate(AuthorDto)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createOrUpdate(authorDto);
    }

    /**
     * Method to delete an author
     * Only an admin can access this resource
     *
     * @param id id of the author to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     * @see AuthorService#delete(Long)
     */
    @Secured("ROLE_ADMIN")
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
