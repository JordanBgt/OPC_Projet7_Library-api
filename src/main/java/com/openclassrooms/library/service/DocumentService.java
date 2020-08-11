package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.AuthorRepository;
import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.PublisherRepository;
import com.openclassrooms.library.dao.predicate.DocumentPredicateBuilder;
import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.*;
import com.openclassrooms.library.entity.criteria.DocumentSearch;
import com.openclassrooms.library.mapper.DocumentMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Base64;

@Service
public class DocumentService {

    Logger log = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Page<DocumentLightDto> findAll(DocumentSearch searchCriteria, Pageable page) {
        Page<DocumentLightDto> results = documentRepository.findAll(DocumentPredicateBuilder.buildSearch(searchCriteria), page)
                .map(documentMapper::toDocumentLightDto);

        results.forEach(document -> {
            if (document.getPhoto() != null) {
                String base64String = this.convertFileToBase64String(fileStorageService.load(document.getPhoto().getName()));
                document.getPhoto().setFileToBase64String(base64String);
            }
        });

        return results;
    }

    public DocumentDto findById(Long id) {
        DocumentDto result = documentMapper.toDocumentDto(documentRepository.findById(id).orElseThrow(EntityNotFoundException::new));

        if (result.getPhoto() != null) {
            String base64String = this.convertFileToBase64String(fileStorageService.load(result.getPhoto().getName()));
            result.getPhoto().setFileToBase64String(base64String);
        }

        return result;
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
        document.setCategory(EDocumentCategory.get(documentDto.getCategory()));
        document.setType(EDocumentType.get(documentDto.getType()));
        document.setDescription(documentDto.getDescription());
        document.setPublicationDate(documentDto.getPublicationDate());
        document.setPublisher(publisher);

        return documentMapper.toDocumentDto(documentRepository.save(document));
    }

    public void delete(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    private String convertFileToBase64String(Resource file) {
        byte[] photoToByteArray = new byte[0];
        try {
            photoToByteArray = IOUtils.toByteArray(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(photoToByteArray);
    }

}
