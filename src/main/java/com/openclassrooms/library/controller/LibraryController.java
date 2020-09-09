package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.LibraryDto;
import com.openclassrooms.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle libraries
 *
 * @see com.openclassrooms.library.entity.Library
 * @see LibraryDto
 * @see LibraryService
 */
@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    /**
     * Method to get all libraries
     * Only an admin can access this resource
     *
     * @return a list of libraries
     * @see LibraryService#findAll()
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<LibraryDto> getAllLibraries() {
        return libraryService.findAll();
    }

    /**
     * Method to get a library
     * Only an admin can access this resource
     *
     * @param id id of the searched library
     *
     * @return a library
     * @see LibraryService#findById(Long)
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public LibraryDto getLibrary(@PathVariable Long id) {
        return libraryService.findById(id);
    }

    /**
     * Method to create a library
     * Only an admin can access this resource
     *
     * @param libraryDto the library to save
     *
     * @return the saved library
     * @see LibraryService#createOrUpdate(LibraryDto)
     */
    @Secured("ROLE_ADMIN")
    @PostMapping
    public LibraryDto createLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createOrUpdate(libraryDto);
    }

    /**
     * Method to update a library
     * Only an admin can access this resource
     *
     * @param libraryDto the library to save
     *
     * @return the updated library
     * @see LibraryService#createOrUpdate(LibraryDto)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public LibraryDto updateLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createOrUpdate(libraryDto);
    }

    /**
     * Method to delete a library
     * Only an admin can access this resource
     *
     * @param id id of the library to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     * @see LibraryService#delete(Long)
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        try {
            libraryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
