package com.openclassrooms.library.dto;

public class PhotoDto {

    private Long id;
    private String path;
    private String extension;

    public PhotoDto() {
    }

    public PhotoDto(Long id, String path, String extension) {
        this.id = id;
        this.path = path;
        this.extension = extension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
