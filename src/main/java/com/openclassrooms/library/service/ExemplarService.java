package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LibraryRepository;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.dto.ExemplarLightDto;
import com.openclassrooms.library.entity.*;
import com.openclassrooms.library.mapper.ExemplarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private LoanRepository loanRepository;

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

    public ExemplarDto findById(Long id) {
        return exemplarMapper.toExemplarDto(exemplarRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<ExemplarLightDto> findAllPendingByDocumentId(Long documentId) {
        return exemplarRepository.findAllByDocumentId(documentId).stream()
                .map(exemplarMapper::toExemplarLightDto)
                .peek(element -> {
                    Loan loan = loanRepository.findByExemplarIdAndState(element.getId(), ELoanState.PENDING);
                    if (loan != null) {
                        element.setLoanEndDate(loan.getEndDate());
                    }
                })
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        exemplarRepository.deleteById(id);
    }

    private String generateReference(EDocumentType type, String isbn) {
        Long timestamp = new Date().getTime();
        isbn = isbn.replace("-", "");
        return type + "_" + isbn + "_" + timestamp;
    }
}
