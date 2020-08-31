package com.openclassrooms.library.dto;

import java.time.LocalDate;

public class ExemplarLightDto {

    private Long id;
    private String reference;
    private LibraryDto library;
    private LocalDate loanEndDate;
    private String documentTitle;

    public ExemplarLightDto() {
    }

    public ExemplarLightDto(Long id, String reference, LibraryDto library, LocalDate loanEndDate, String documentTitle) {
        this.id = id;
        this.reference = reference;
        this.library = library;
        this.loanEndDate = loanEndDate;
        this.documentTitle = documentTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }

    public LocalDate getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(LocalDate loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
}
