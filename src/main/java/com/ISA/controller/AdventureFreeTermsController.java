package com.ISA.controller;

import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.service.implementation.AdventureFreeTermsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventureFreeTerms")
public class AdventureFreeTermsController {

    @Autowired
    private AdventureFreeTermsServiceImpl adventureFreeTermsService;

    @GetMapping(path = "/{adventureId}")
    public ResponseEntity<?> getAll(@PathVariable Long adventureId) {
        List<AdventureFreeTerms> adventureFreeTerms = adventureFreeTermsService.getAll(adventureId);

        return new ResponseEntity<>(adventureFreeTerms, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureFreeTermsDTO dto) {
        AdventureFreeTerms adventureFreeTerms = adventureFreeTermsService.add(dto);

        return new ResponseEntity<>(adventureFreeTerms, HttpStatus.OK);
    }
}
