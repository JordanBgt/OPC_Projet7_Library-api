package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LibraryRepository;
import com.openclassrooms.library.dto.ExemplarAvailableDto;
import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.entity.*;
import com.openclassrooms.library.mapper.ExemplarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service to manage exemplars
 *
 * @see Exemplar
 * @see ExemplarDto
 * @see ExemplarAvailableDto
 * @see ExemplarRepository
 * @see ExemplarMapper
 * @see DocumentRepository
 * @see LibraryRepository
 */
@Service
public class ExemplarService {

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private ExemplarMapper exemplarMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    /**
     * Method to create or update an exemplar
     *
     * @param exemplarDto the exemplar to save
     *
     * @return the saved exemplar
     * @see ExemplarRepository#save(Object)
     */
    public ExemplarDto createOrUpdate(ExemplarDto exemplarDto) {
        Exemplar exemplar;
        Document document = documentRepository.findById(exemplarDto.getDocument().getId()).orElseThrow(EntityNotFoundException::new);
        Library library = libraryRepository.findById(exemplarDto.getLibrary().getId()).orElseThrow(EntityNotFoundException::new);
        if (exemplarDto.getId() != null) {
            exemplar = exemplarRepository.findById(exemplarDto.getId()).orElseThrow(EntityNotFoundException::new);
            exemplar.setReference(exemplarDto.getReference());
        } else {
            exemplar = new Exemplar();
            exemplar.setReference(generateReference(document.getType(), document.getIsbn()));
        }
        exemplar.setDocument(document);
        exemplar.setLibrary(library);
        return exemplarMapper.toExemplarDto(exemplarRepository.save(exemplar));
    }

    /**
     * Method to retrieve an exemplar by its id
     *
     * @param id id of the requested exemplar
     *
     * @return an exemplar
     * @see ExemplarRepository#findById(Object)
     */
    public ExemplarDto findById(Long id) {
        return exemplarMapper.toExemplarDto(exemplarRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Method to find all the available exemplars of a document
     *
     * @param documentId id of the document for which we are looking for exemplars
     *
     * @return a list of ExemplarAvailableDto (number of exemplars grouped by library)
     * @see ExemplarRepository#findAllAvailableByDocumentId(Long)
     */
    public List<ExemplarAvailableDto> findAllAvailableByDocumentId(Long documentId) {
        return new ArrayList<>(exemplarRepository.findAllAvailableByDocumentId(documentId));
    }

    /**
     * Method to delete an exemplar by its id
     *
     * @param id id of the exemplar to delete
     * @see ExemplarRepository#deleteById(Object)
     */
    public void delete(Long id) {
        exemplarRepository.deleteById(id);
    }

    /**
     * Method to generate a unique reference for an exemplar. For example (BOOK_9782070612758_1597229871157)
     *
     * @param type document type
     * @param isbn document isbn
     *
     * @return a string
     */
    private String generateReference(EDocumentType type, String isbn) {
        Long timestamp = new Date().getTime();
        isbn = isbn.replace("-", "");
        return type + "_" + isbn + "_" + timestamp;
    }
}
