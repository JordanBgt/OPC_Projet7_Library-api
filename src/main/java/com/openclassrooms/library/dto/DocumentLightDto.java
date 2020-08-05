package com.openclassrooms.library.dto;

public class DocumentLightDto {

    // TODO : ajouter photo
    private String title;
    private String author; // nom + prénom auteur

    public DocumentLightDto() {
    }

    public DocumentLightDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "DocumentLightDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
