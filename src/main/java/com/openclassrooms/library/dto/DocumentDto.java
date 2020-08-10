package com.openclassrooms.library.dto;

import com.openclassrooms.library.entity.EDocumentCategory;
import com.openclassrooms.library.entity.EDocumentType;
;

import java.time.LocalDate;

public class DocumentDto {

    // TODO : ajouter photo
    private Long id;
    private String title;
    private String isbn;
    private String description;
    private LocalDate publicationDate;
    private PublisherDto publisher;
    private AuthorDto author;
    private String type;
    private String category;

    public DocumentDto() {
    }

    public DocumentDto(Long id, String title, String isbn, String description, LocalDate publicationDate,
                       PublisherDto publisher, AuthorDto author, String type, String category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.author = author;
        this.type = type;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public PublisherDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDto publisher) {
        this.publisher = publisher;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
