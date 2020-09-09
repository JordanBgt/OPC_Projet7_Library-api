package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle loans
 *
 * @see com.openclassrooms.library.entity.Loan
 * @see LoanDto
 * @see LoanService
 */
@RestController
@RequestMapping("api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Method to get all loans
     * Only an admin can access this resource
     *
     * @return a list of loans
     * @see LoanService#findAll()
     */
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<LoanDto> getAllLoans() {
        return loanService.findAll();
    }

    /**
     * Method to get all current loans for a user
     * Only an admin or a connected user can access this resource
     *
     * @param userId user's id
     *
     * @return list of loans
     * @see LoanService#findAllPendingByUserId(Long)
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/users/{userId}")
    public List<LoanDto> getAllByUser(@PathVariable Long userId) {
        return loanService.findAllPendingByUserId(userId);
    }

    /**
     * Method to get all ended loans
     *
     * @return a list of loans
     * @see LoanService#findAllEndedLoans()
     */
    @GetMapping("/ended")
    public List<LoanDto> getAllEndedLoans() {
        return loanService.findAllEndedLoans();
    }

    /**
     * Method to create a loan
     * Only an admin can access this resource
     *
     * @param loanDto the loan to save
     *
     * @return the saved loan
     * @see LoanService#createOrUpdate(LoanDto)
     */
    @Secured("ROLE_ADMIN")
    @PostMapping
    public LoanDto createLoan(@RequestBody LoanDto loanDto) {
        return loanService.createOrUpdate(loanDto);
    }

    /**
     * Method to extend a loan
     * Only and admin or a connected user can access this resource
     *
     * @param id id of the loan to extend
     *
     * @return a response entity with a status representing the success or failure of the extension
     * @see LoanService#renew(Long)
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}/renewal")
    public ResponseEntity<Void> renewLoan(@PathVariable Long id) {
        try {
            loanService.renew(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Method to return an exemplar
     * Only an admin can access this resource
     *
     * @param id id of the loan
     *
     * @return a response entity with no content status
     * @see LoanService#returnExemplar(Long)
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}/return")
    public ResponseEntity<Void> returnExemplar(@PathVariable Long id) {
        loanService.returnExemplar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Method to update a loan
     * Only an admin can update a loan
     *
     * @param loanDto the loan to save
     *
     * @return the updated loan
     * @see LoanService#createOrUpdate(LoanDto)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public LoanDto updateLoan(@RequestBody LoanDto loanDto) {
        return loanService.createOrUpdate(loanDto);
    }

    /**
     * Method to delete a loan
     * Only an admin can access this resource
     *
     * @param id id of the loan to delete
     *
     * @return a response entity with a status representing the success or failure of the deletion
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        try {
            loanService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
