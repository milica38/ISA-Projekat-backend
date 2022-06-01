package com.ISA.controller;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.dto.converters.LoyaltyProgrammeConverters;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.LoyaltyProgramme;
import com.ISA.service.implementation.HomeProfileServiceImpl;
import com.ISA.service.implementation.LoyaltyProgrammeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/loyalty")
public class LoyaltyProgrammeController {

    @Autowired
    private LoyaltyProgrammeServiceImpl loyaltyProgrammeService;




    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody LoyaltyProgrammeDTO dto) {
        LoyaltyProgramme lp = loyaltyProgrammeService.add(dto);

        return new ResponseEntity<>(LoyaltyProgrammeConverters.modelToDTO(lp), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody LoyaltyProgrammeDTO dto) {
        LoyaltyProgramme lp = loyaltyProgrammeService.edit(id, dto);

        return new ResponseEntity<>(lp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = loyaltyProgrammeService.delete(id);

        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}

