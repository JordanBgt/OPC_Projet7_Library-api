package com.openclassrooms.library.entity.criteria;

import com.openclassrooms.library.entity.EDocumentCategory;
import com.openclassrooms.library.entity.EDocumentType;

public class DocumentSearch {

    private String title;
    private String isbn;
    private String authorLastName;
    private String publisherName;
    private EDocumentType type;
    private EDocumentCategory category;

    public DocumentSearch() {
    }

    public DocumentSearch(String title, String isbn, String authorLastName, String publisherName, EDocumentType type, EDocumentCategory category) {
        this.title = title;
        this.isbn = isbn;
        this.authorLastName = authorLastName;
        this.publisherName = publisherName;
        this.type = type;
        this.category = category;
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

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public EDocumentType getType() {
        return type;
    }

    public void setType(EDocumentType type) {
        this.type = type;
    }

    public EDocumentCategory getCategory() {
        return category;
    }

    public void setCategory(EDocumentCategory category) {
        this.category = category;
    }
}
