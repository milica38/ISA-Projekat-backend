package com.ISA.controller;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.dto.IncomeDTO;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.domain.model.Income;
import com.ISA.service.definition.HomeComplaintsService;
import com.ISA.service.definition.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('PredefinedAdmin')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody IncomeDTO dto) {
        Income income = incomeService.add(dto);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllIncomes")
    public ResponseEntity<?> getAllIncomes() {
        List<Income> income = incomeService.getAllIncomes();
        return new ResponseEntity<>(income, HttpStatus.OK);
    }
}