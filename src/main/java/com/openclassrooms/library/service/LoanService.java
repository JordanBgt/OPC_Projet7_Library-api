package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.entity.ELoanState;
import com.openclassrooms.library.entity.Exemplar;
import com.openclassrooms.library.entity.Loan;
import com.openclassrooms.library.entity.User;
import com.openclassrooms.library.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
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

    public List<LoanDto> findAll() {
        return loanMapper.toListLoanDto(loanRepository.findAll());
    }

    public List<LoanDto> findAllPendingByUserId(Long userId) {
        return loanRepository.findAllByUserIdAndState(userId, ELoanState.PENDING).stream().map(loanMapper::toLoanDto).collect(Collectors.toList());
    }

    public List<LoanDto> findAllEndedLoans() {
        return loanRepository.findAllEndedLoans().stream().map(loanMapper::toLoanDto).collect(Collectors.toList());
    }

    public LoanDto createOrUpdate(LoanDto loanDto) {
        Loan loan;
        if (loanDto.getId() != null) {
            loan = loanRepository.findById(loanDto.getId()).orElseThrow(EntityNotFoundException::new);
            loan.setStartDate(loanDto.getStartDate());
            loan.setEndDate(loanDto.getEndDate());
        } else {
            loan = new Loan();
            loan.setStartDate(LocalDate.now());
            loan.setEndDate(loan.getStartDate().plusWeeks(4));
        }
        Exemplar exemplar = exemplarRepository.findById(loanDto.getExemplar().getId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(loanDto.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        loan.setExemplar(exemplar);
        loan.setUser(user);
        loan.setRenewed(loanDto.isRenewed());
        loan.setState(ELoanState.PENDING);

        return loanMapper.toLoanDto(loanRepository.save(loan));
    }

    public void renew(Long id) throws Exception {
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (loan.isRenewed()) {
            // TODO : exception vu que le prêt a déjà été renouvelé une fois
            throw new Exception("Prêt déjà revouvelé");
        } else {
            loan.setRenewed(true);
            loan.setEndDate(loan.getEndDate().plusWeeks(4));
            loanRepository.save(loan);
        }
    }

    public void returnDocument(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        loan.setState(ELoanState.FINISHED);
        loanRepository.save(loan);
    }

    public void delete(Long loanId) {
        loanRepository.deleteById(loanId);
    }

}
