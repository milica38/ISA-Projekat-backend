package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.dto.IncomeDTO;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.domain.model.HomeReservation;
import com.ISA.domain.model.Income;
import com.ISA.domain.model.User;
import com.ISA.repository.IncomeRepository;
import com.ISA.service.definition.IncomeService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private UserService userService;

    @Autowired
    private IncomeRepository incomeRepository;

    public Income add(IncomeDTO dto) {

        List<Income> optionalIncome = incomeRepository.findAll();

        if(optionalIncome.isEmpty()) {
            Income income = new Income();
            income.setId(dto.getId());
            income.setPercentage(dto.getPercentage());

            return incomeRepository.save(income);
        } else {

            optionalIncome.get(0).setPercentage(dto.getPercentage());

            return incomeRepository.save(optionalIncome.get(0));
        }
    }

    public List<Income> getAllIncomes() {

        return incomeRepository.findAll();

    }

}
