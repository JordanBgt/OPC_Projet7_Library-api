package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.PublisherDto;
import com.openclassrooms.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Secured("ADMIN")
    @GetMapping
    public List<PublisherDto> getAllPublishers() {
        return publisherService.findAll();
    }

    @Secured("ADMIN")
    @GetMapping("/{id}")
    public PublisherDto getPublisher(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @Secured("ADMIN")
    @PostMapping
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createOrUpdate(publisherDto);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public PublisherDto updatePublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createOrUpdate(publisherDto);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        try {
            publisherService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
