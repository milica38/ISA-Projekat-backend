package com.ISA.controller;

import com.ISA.domain.dto.LoyalProgrammeDTO;
import com.ISA.domain.dto.LoyaltyProgrammeDTO;
import com.ISA.domain.dto.converters.AdventureProfileConverters;
import com.ISA.domain.dto.converters.LoyalProgrammeConverters;
import com.ISA.domain.dto.converters.LoyaltyProgrammeConverters;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.LoyalProgramme;
import com.ISA.domain.model.LoyaltyProgramme;
import com.ISA.service.implementation.LoyalProgrammeServiceImpl;
import com.ISA.service.implementation.LoyaltyProgrammeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/loyal")
public class LoyalProgrammeController {

    @Autowired
    private LoyalProgrammeServiceImpl loyalProgrammeService;




    @PreAuthorize("hasAuthority('Admin') or hasAuthority('PredefinedAdmin')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody LoyalProgrammeDTO dto) {
        LoyalProgramme lp = loyalProgrammeService.add(dto);

        return new ResponseEntity<>(LoyalProgrammeConverters.modelToDTO(lp), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('PredefinedAdmin')")
    @PutMapping(path = "/editLoyalty/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody LoyalProgrammeDTO dto) {
        LoyalProgramme lp = loyalProgrammeService.edit(id, dto);

        return new ResponseEntity<>(lp, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('PredefinedAdmin')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean delete = loyalProgrammeService.delete(id);

        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @GetMapping(path = "/loyalties")
    public ResponseEntity<?> getAll() {
        List<LoyalProgramme> loyalties = loyalProgrammeService.getAll();

        return new ResponseEntity<>(LoyalProgrammeConverters.modelsToDTOs(loyalties), HttpStatus.OK);
    }

    @GetMapping(path = "/oneLoyalty/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        LoyalProgramme lp = loyalProgrammeService.get(id);

        return new ResponseEntity<>(LoyalProgrammeConverters.modelToDTO(lp), HttpStatus.OK);
    }

}
