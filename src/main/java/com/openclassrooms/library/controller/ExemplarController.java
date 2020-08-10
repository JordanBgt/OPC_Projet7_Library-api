package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.service.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/exemplars")
public class ExemplarController {

    @Autowired
    private ExemplarService exemplarService;

    @GetMapping
    public List<ExemplarDto> getAllExemplarByDocumentId(@RequestParam Long documentId) {
        return exemplarService.findAllByDocumentId(documentId);
    }

    @GetMapping("/{id}")
    public ExemplarDto getExemplar(@PathVariable Long id) {
        return exemplarService.findById(id);
    }

    @PostMapping
    public ExemplarDto createExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarService.createOrUpdate(exemplarDto);
    }

    @PutMapping("/{id}")
    public ExemplarDto updateExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarService.createOrUpdate(exemplarDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExemplar(@PathVariable Long id) {
        try {
            exemplarService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
