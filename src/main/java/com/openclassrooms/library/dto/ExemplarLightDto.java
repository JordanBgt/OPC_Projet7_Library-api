package com.openclassrooms.library.dto;

public class ExemplarLightDto {

    private Long id;
    private String reference;
    private LibraryDto library;

    public ExemplarLightDto() {
    }

    public ExemplarLightDto(Long id, String reference, LibraryDto library) {
        this.id = id;
        this.reference = reference;
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

    public LibraryDto getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDto library) {
        this.library = library;
    }
}
