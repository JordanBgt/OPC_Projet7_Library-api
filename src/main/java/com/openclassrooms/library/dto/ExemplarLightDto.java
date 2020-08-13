package com.openclassrooms.library.dto;

import java.time.LocalDate;

public class ExemplarLightDto {

    private Long id;
    private String reference;
    private LibraryDto library;
    private LocalDate loanEndDate;

    public ExemplarLightDto() {
    }

    public ExemplarLightDto(Long id, String reference, LibraryDto library, LocalDate loanEndDate) {
        this.id = id;
        this.reference = reference;
        this.library = library;
        this.loanEndDate = loanEndDate;
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
}
