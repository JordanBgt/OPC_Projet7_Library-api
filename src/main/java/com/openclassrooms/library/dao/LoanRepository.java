package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.ELoanState;
import com.openclassrooms.library.entity.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findAll();

    List<Loan> findAllByUserIdAndStateOrderByEndDateAsc(Long userId, ELoanState state);

    @Query(value = "SELECT * FROM loan WHERE end_date < CURDATE()", nativeQuery = true)
    List<Loan> findAllEndedLoans();
}
