package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.ExemplarAvailableDto;
import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.service.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle exemplars
 *
 * @see com.openclassrooms.library.entity.Exemplar
 * @see ExemplarDto
 * @see ExemplarAvailableDto
 * @see ExemplarService
 */
@RestController
@RequestMapping("api/exemplars")
public class ExemplarController {

    @Autowired
    private ExemplarService exemplarService;

    /**
     * Method that returns the available exemplars of a document
     *
     * @param documentId id of the document for which exemplaires are sought
     *
     * @return a list of available exemplars
     * @see ExemplarService#findAllAvailableByDocumentId(Long)
     */
    @GetMapping
    public List<ExemplarAvailableDto> getAllAvailableExemplarByDocumentId(@RequestParam Long documentId) {
        return exemplarService.findAllAvailableByDocumentId(documentId);
    }

    /**
     * Method to get an exemplar
     *
     * @param id id of the searched exemplar
     *
     * @return an exemplar
     * @see ExemplarService#findById(Long)
     */
    @GetMapping("/{id}")
    public ExemplarDto getExemplar(@PathVariable Long id) {
        return exemplarService.findById(id);
    }

    /**
     * Method to create an exemplar
     * Only an admin can access this resource
     *
     * @param exemplarDto the exemplar to save
     *
     * @return the saved exemplar
     * @see ExemplarService#createOrUpdate(ExemplarDto)
     */
    @Secured("ROLE_ADMIN")
    @PostMapping
    public ExemplarDto createExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarService.createOrUpdate(exemplarDto);
    }

    /**
     * Method to update an exemplar
     * Only an admin can access this resource
     *
     * @param exemplarDto the exemplar to save
     *
     * @return the updated exemplar
     * @see ExemplarService#createOrUpdate(ExemplarDto)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ExemplarDto updateExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarService.createOrUpdate(exemplarDto);
    }

    /**
     * Method to delete an exemplar
     * Only an admin can access this resource
     *
     * @param id id of the exemplar to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     * @see ExemplarService#delete(Long)
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExemplar(@PathVariable Long id) {
        try {
            exemplarService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
