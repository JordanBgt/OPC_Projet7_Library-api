package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.criteria.DocumentSearch;
import com.openclassrooms.library.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle documents
 *
 * @see com.openclassrooms.library.entity.Document
 * @see DocumentDto
 * @see DocumentLightDto
 * @see DocumentService
 */
@RestController
@RequestMapping("api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    /**
     * Method that returns a page of documents
     *
     * @param title search criteria if the user want a page of documents whose title corresponds to the given title
     * @param isbn search criteria if the user want a page of documents whose isbn corresponds to the given isbn
     * @param authorName search criteria if the user want a page of documents whose author's name corresponds to the given name
     * @param publisherName search criteria if the user want a page of documents whose publisher's name corresponds to the given name
     * @param type search criteria if the user want a page of documents that belongs to the given type
     * @param category search criteria if the user want a page of documents that belongs to the given category
     * @param page page number requested. Default value : 0
     * @param size number of comments per page. Default value : 6
     * @param sortBy sorting criteria. Default value : title
     * @param direction sorting direction criteria. Default value : ASC
     * @param unpaged boolean which represents whether the user want a paginated result or not. Default value : false
     *                
     * @return a page of documents
     * @see DocumentSearch
     * @see DocumentService#findAll(DocumentSearch, Pageable) 
     */
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

    /**
     * Method to get a document
     * 
     * @param id id of the searched document
     *           
     * @return a document
     * @see DocumentService#findById(Long) 
     */
    @GetMapping("/{id}")
    public DocumentDto getDocument(@PathVariable Long id) {
        return documentService.findById(id);
    }

    /**
     * Method to create a document
     * Only an admin can access this resource
     * 
     * @param documentDto the document to save
     *                    
     * @return the saved document
     * @see DocumentService#createOrUpdate(DocumentDto) 
     */
    @Secured("ROLE_ADMIN")
    @PostMapping
    public DocumentDto createDocument(@RequestBody DocumentDto documentDto) {
        return documentService.createOrUpdate(documentDto);
    }

    /**
     * Method to update a document
     * Only an admin can access this resource
     *
     * @param documentDto the document to save
     *
     * @return the updated document
     * @see DocumentService#createOrUpdate(DocumentDto)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public DocumentDto updateDocument(@RequestBody DocumentDto documentDto) {
        return documentService.createOrUpdate(documentDto);
    }

    /**
     * Method to delete an document
     * Only an admin can access this resource
     *
     * @param id id of the document to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     * @see DocumentService#delete(Long) 
     */
    @Secured("ROLE_ADMIN")
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
