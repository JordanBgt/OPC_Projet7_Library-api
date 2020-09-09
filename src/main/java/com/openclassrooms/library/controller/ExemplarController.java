package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.dto.ExemplarLightDto;
import com.openclassrooms.library.service.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exemplars")
public class ExemplarController {

    @Autowired
    private ExemplarService exemplarService;

    @GetMapping
    public List<ExemplarLightDto> getAllExemplarByDocumentId(@RequestParam Long documentId) {
        return exemplarService.findAllPendingByDocumentId(documentId);
    }

    @GetMapping("/{id}")
    public ExemplarDto getExemplar(@PathVariable Long id) {
        return exemplarService.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ExemplarDto createExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarService.createOrUpdate(exemplarDto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ExemplarDto updateExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarService.createOrUpdate(exemplarDto);
    }

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
