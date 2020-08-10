package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/users/{userId}")
    public List<LoanDto> getAllByUser(@PathVariable Long userId) {
        return loanService.findAllByUserId(userId);
    }

    @PostMapping
    public LoanDto createLoan(@RequestBody LoanDto loanDto) {
        return loanService.createOrUpdate(loanDto);
    }

    @PutMapping("/{id}")
    public LoanDto updateLoan(@RequestBody LoanDto loanDto) {
        return loanService.createOrUpdate(loanDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        try {
            loanService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}