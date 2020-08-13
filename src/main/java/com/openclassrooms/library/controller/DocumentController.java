package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.EDocumentCategory;
import com.openclassrooms.library.entity.EDocumentType;
import com.openclassrooms.library.entity.criteria.DocumentSearch;
import com.openclassrooms.library.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public Page<DocumentLightDto> getAllDocuments(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String isbn,
                                                  @RequestParam(required = false) String authorName,
                                                  @RequestParam(required = false) String publisherName,
                                                  @RequestParam(required = false) String type,
                                                  @RequestParam(required = false) String category,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "6") Integer size,
                                                  @RequestParam(defaultValue = "title") String sortBy,
                                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                  @RequestParam(defaultValue = "false") boolean unpaged) {
        DocumentSearch searchCriteria = new DocumentSearch(title, isbn, authorName, publisherName, type, category);
        Pageable pageable = unpaged ? Pageable.unpaged() : PageRequest.of(page, size, direction, sortBy);
        return documentService.findAll(searchCriteria, pageable);

    }

    @GetMapping("/{id}")
    public DocumentDto getDocument(@PathVariable Long id) {
        return documentService.findById(id);
    }

    @PostMapping
    public DocumentDto createDocument(@RequestBody DocumentDto documentDto) {
        return documentService.createOrUpdate(documentDto);
    }

    @PutMapping("/{id}")
    public DocumentDto updateDocument(@RequestBody DocumentDto documentDto) {
        return documentService.createOrUpdate(documentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        try {
            documentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

}
