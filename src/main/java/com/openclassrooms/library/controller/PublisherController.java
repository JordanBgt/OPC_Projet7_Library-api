package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.PublisherDto;
import com.openclassrooms.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle publishers
 *
 * @see com.openclassrooms.library.entity.Publisher
 * @see PublisherDto
 * @see PublisherService
 */
@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    /**
     * Method that returns all publishers
     * Only an admin can access this resource
     *
     * @return a list of publishers
     * @see PublisherService#findAll()
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<PublisherDto> getAllPublishers() {
        return publisherService.findAll();
    }

    /**
     * Method to get a publisher
     * Only an admin can access this resource
     *
     * @param id id of the searched publisher
     *
     * @return a publisher
     * @see PublisherService#findById(Long)
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public PublisherDto getPublisher(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    /**
     * Method to create a publisher
     * Only an admin can access this resource
     *
     * @param publisherDto the publisher to save
     *
     * @return the saved publisher
     * @see PublisherService#createOrUpdate(PublisherDto)
     */
    @Secured("ROLE_ADMIN")
    @PostMapping
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createOrUpdate(publisherDto);
    }

    /**
     * Method to update a publisher
     * Only an admin can access this resource
     *
     * @param publisherDto the publisher to save
     *
     * @return the updated publisher
     * @see PublisherService#createOrUpdate(PublisherDto)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public PublisherDto updatePublisher(@RequestBody PublisherDto publisherDto) {
        return publisherService.createOrUpdate(publisherDto);
    }

    /**
     * Method to delete a publisher
     * Only an admin can access this resource
     *
     * @param id id of the publisher to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     * @see PublisherService#delete(Long)
     */
    @Secured("ROLE_ADMIN")
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
