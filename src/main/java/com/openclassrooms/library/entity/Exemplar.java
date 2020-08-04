package com.openclassrooms.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "exemplar")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 45)
    private String reference;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Document document;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Library library;

    public Exemplar() {}

    public Exemplar(Long id, String reference, Document document, Library library) {
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
