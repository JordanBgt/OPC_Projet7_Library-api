package com.openclassrooms.library.dto;

import com.openclassrooms.library.entity.Library;

public class ExemplarDto {

    private Long id;
    private String reference;
    private DocumentDto document;
    private LibraryDto library;

    public ExemplarDto() {
    }

    public ExemplarDto(Long id, String reference, DocumentDto document, LibraryDto library) {
        this.id = id;
        this.reference = reference;
        this.document = document;
        this.library = library;
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

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }
}
