package com.openclassrooms.library.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private boolean renewed;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Exemplar exemplar;

    @Enumerated(EnumType.STRING)
    private ELoanState state;

    public Loan() {
    }

    public Loan(Long id, LocalDate startDate, LocalDate endDate, boolean renewed, User user, Exemplar exemplar, ELoanState state) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.renewed = renewed;
        this.user = user;
        this.exemplar = exemplar;
        this.state = state;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public ELoanState getState() {
        return state;
    }

    public void setState(ELoanState state) {
        this.state = state;
    }
}
