package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.LibraryDto;
import com.openclassrooms.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<LibraryDto> getAllLibraries() {
        return libraryService.findAll();
    }

    @GetMapping("/{id}")
    public LibraryDto getLibrary(@PathVariable Long id) {
        return libraryService.findById(id);
    }

    @PostMapping
    public LibraryDto createLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createOrUpdate(libraryDto);
    }

    @PutMapping("/{id}")
    public LibraryDto updateLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createOrUpdate(libraryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        try {
            libraryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
