package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.AuthorRepository;
import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.PublisherRepository;
import com.openclassrooms.library.dao.predicate.DocumentPredicateBuilder;
import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.Author;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.Publisher;
import com.openclassrooms.library.entity.criteria.DocumentSearch;
import com.openclassrooms.library.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public Page<DocumentLightDto> findAll(DocumentSearch searchCriteria, Pageable page) {
        return documentRepository.findAll(DocumentPredicateBuilder.buildSearch(searchCriteria), page)
                .map(documentMapper::toDocumentLightDto);
    }

    public DocumentDto findById(Long id) {
        return documentMapper.toDocumentDto(documentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public DocumentDto createOrUpdate(DocumentDto documentDto) {
        Document document;
        if (documentDto.getId() != null) {
            document = documentRepository.findById(documentDto.getId()).orElseThrow(EntityNotFoundException::new);
        } else {
            document = new Document();
        }
        Author author = authorRepository.findById(documentDto.getAuthor().getId()).orElseThrow(EntityNotFoundException::new);
        Publisher publisher = publisherRepository.findById(documentDto.getPublisher().getId()).orElseThrow(EntityNotFoundException::new);
        document.setIsbn(documentDto.getIsbn());
        document.setAuthor(author);
        document.setTitle(documentDto.getTitle());
        document.setCategory(documentDto.getCategory());
        document.setType(documentDto.getType());
        document.setDescription(documentDto.getDescription());
        document.setPublicationDate(documentDto.getPublicationDate());
        document.setPublisher(publisher);

        return documentMapper.toDocumentDto(documentRepository.save(document));
    }

    public void delete(Long documentId) {
        documentRepository.deleteById(documentId);
    }

}
