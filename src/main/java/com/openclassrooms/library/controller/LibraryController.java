package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.LibraryDto;
import com.openclassrooms.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<LibraryDto> getAllLibraries() {
        return libraryService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public LibraryDto getLibrary(@PathVariable Long id) {
        return libraryService.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public LibraryDto createLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createOrUpdate(libraryDto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public LibraryDto updateLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createOrUpdate(libraryDto);
    }

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
