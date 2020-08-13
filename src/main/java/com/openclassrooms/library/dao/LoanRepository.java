package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findAllByUserId(Long userId);
    Loan findByExemplarId(Long exemplarId);
}
