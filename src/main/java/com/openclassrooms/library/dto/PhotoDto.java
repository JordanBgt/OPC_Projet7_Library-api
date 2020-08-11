package com.openclassrooms.library.dto;

public class PhotoDto {

    private Long id;
    private String name;
    private String extension;
    private String fileToBase64String;

    public PhotoDto() {
    }

    public PhotoDto(Long id, String name, String extension, String fileToBase64String) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.fileToBase64String = fileToBase64String;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFileToBase64String() {
        return fileToBase64String;
    }

    public void setFileToBase64String(String fileToBase64String) {
        this.fileToBase64String = fileToBase64String;
    }
}
