package com.openclassrooms.library.dto;

public class DocumentLightDto {

    private Long id;
    private String title;
    private String authorLastName;
    private String authorFirstName;
    private PhotoDto photo;

    public DocumentLightDto() {
    }

    public DocumentLightDto(Long id, String title, String authorLastName, String authorFirstName, PhotoDto photo) {
        this.id = id;
        this.title = title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.photo = photo;
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

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public PhotoDto getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDto photo) {
        this.photo = photo;
    }
}
