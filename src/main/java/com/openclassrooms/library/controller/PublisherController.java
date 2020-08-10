package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.PublisherDto;
import com.openclassrooms.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<PublisherDto> getAllPublishers() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    public PublisherDto getPublisher(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @PostMapping
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createOrUpdate(publisherDto);
    }

    @PutMapping("/{id}")
    public PublisherDto updatePublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createOrUpdate(publisherDto);
    }

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
