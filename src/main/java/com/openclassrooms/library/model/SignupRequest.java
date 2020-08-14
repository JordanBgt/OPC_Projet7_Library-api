package com.openclassrooms.library.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    private String role;

    @NotBlank
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(@NotBlank String username, @NotBlank @Email String email, String role, @NotBlank String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
