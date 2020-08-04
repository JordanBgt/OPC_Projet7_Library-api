package com.openclassrooms.library.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(nullable = false)
    @Type(type = "text")
    private String description;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EDocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EDocumentCategory documentCategory;

    public Document() {}

    public Document(Long id, String title, String isbn, String description, LocalDate publicationDate,
                    Publisher publisher, Author author, Photo photo, EDocumentType documentType,
                    EDocumentCategory documentCategory) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.author = author;
        this.photo = photo;
        this.documentType = documentType;
        this.documentCategory = documentCategory;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public EDocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(EDocumentType documentType) {
        this.documentType = documentType;
    }

    public EDocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(EDocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }
}
