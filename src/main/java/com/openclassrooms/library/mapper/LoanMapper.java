package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.entity.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DocumentMapper.class})
public interface LoanMapper {

    LoanDto toLoanDto(Loan loan);
    Loan toLoan(LoanDto loanDto);
}
