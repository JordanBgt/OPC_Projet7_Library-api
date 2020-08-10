package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.entity.Exemplar;
import com.openclassrooms.library.entity.Loan;
import com.openclassrooms.library.entity.User;
import com.openclassrooms.library.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LoanDto> findAllByUserId(Long userId) {
        return loanRepository.findAllByUserId(userId).stream().map(loanMapper::toLoanDto).collect(Collectors.toList());
    }

    public LoanDto createOrUpdate(LoanDto loanDto) {
        Loan loan;
        if (loanDto.getId() != null) {
            loan = loanRepository.findById(loanDto.getId()).orElseThrow(EntityNotFoundException::new);
        } else {
            loan = new Loan();
        }
        Exemplar exemplar = exemplarRepository.findById(loanDto.getExemplar().getId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(loanDto.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        loan.setExemplar(exemplar);
        loan.setUser(user);
        loan.setStartDate(loanDto.getStartDate());
        loan.setEndDate(loanDto.getEndDate());
        loan.setRenewed(loanDto.isRenewed());

        return loanMapper.toLoanDto(loanRepository.save(loan));
    }

    public void delete(Long loanId) {
        loanRepository.deleteById(loanId);
    }

}
