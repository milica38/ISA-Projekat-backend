package com.ISA.service.definition;


import com.ISA.domain.dto.IncomeDTO;
import com.ISA.domain.model.Income;

import java.util.List;

public interface IncomeService {
    Income add(IncomeDTO dto);
    List<Income> getAllIncomes();
}
