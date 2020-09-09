package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.entity.Loan;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DocumentMapper.class, ExemplarMapper.class})
public interface LoanMapper {

    LoanDto toLoanDto(Loan loan);
    List<LoanDto> toListLoanDto(List<Loan> loans);
}
