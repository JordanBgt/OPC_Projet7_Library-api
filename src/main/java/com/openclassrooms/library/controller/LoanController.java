package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Secured({"ADMIN", "USER"})
    @GetMapping("/users/{userId}")
    public List<LoanDto> getAllByUser(@PathVariable Long userId) {
        return loanService.findAllByUserId(userId);
    }

    @Secured("ADMIN")
    @PostMapping
    public LoanDto createLoan(@RequestBody LoanDto loanDto) {
        return loanService.createOrUpdate(loanDto);
    }

    @Secured({"ADMIN", "USER"})
    @GetMapping("/{id}/renewal")
    public ResponseEntity<Void> renewLoan(@PathVariable Long id) {
        try {
            loanService.renew(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public LoanDto updateLoan(@RequestBody LoanDto loanDto) {
        return loanService.createOrUpdate(loanDto);
    }

    @Secured("ADMIN")
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
