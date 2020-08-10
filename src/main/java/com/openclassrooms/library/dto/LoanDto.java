package com.openclassrooms.library.dto;

import java.time.LocalDate;

public class LoanDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean renewed;
    private UserLightDto user;
    private ExemplarLightDto exemplar;

    public LoanDto() {
    }

    public LoanDto(Long id, LocalDate startDate, LocalDate endDate, boolean renewed, UserLightDto user, ExemplarLightDto exemplar) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.renewed = renewed;
        this.user = user;
        this.exemplar = exemplar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }

    public UserLightDto getUser() {
        return user;
    }

    public void setUser(UserLightDto user) {
        this.user = user;
    }

    public ExemplarLightDto getExemplar() {
        return exemplar;
    }

    public void setExemplar(ExemplarLightDto exemplar) {
        this.exemplar = exemplar;
    }
}
