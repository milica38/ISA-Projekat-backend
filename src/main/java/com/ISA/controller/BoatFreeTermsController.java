package com.ISA.controller;

import com.ISA.domain.dto.BoatFreeTermsDTO;
import com.ISA.domain.model.BoatFreeTerms;
import com.ISA.service.implementation.BoatFreeTermsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatterms")
public class BoatFreeTermsController {

    @Autowired
    private BoatFreeTermsServiceImpl boatFreeTermsService;

    @PreAuthorize("hasAuthority('Boat owner')")
    @GetMapping(path = "/{boatId}")
    public ResponseEntity<?> getAll(@PathVariable Long boatId) {
        List<BoatFreeTerms> boatFreeTerms = boatFreeTermsService.getAll(boatId);
        return new ResponseEntity<>(boatFreeTerms, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Boat owner')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody BoatFreeTermsDTO dto) {
        BoatFreeTerms boatFreeTerms = boatFreeTermsService.add(dto);

        return new ResponseEntity<>(boatFreeTerms, HttpStatus.OK);
    }
}
