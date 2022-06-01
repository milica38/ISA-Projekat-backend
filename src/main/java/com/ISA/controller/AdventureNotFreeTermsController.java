package com.ISA.controller;

import com.ISA.domain.dto.AdventureFreeTermsDTO;
import com.ISA.domain.dto.AdventureNotFreeTermsDTO;
import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.domain.model.AdventureNotFreeTerms;
import com.ISA.service.implementation.AdventureFreeTermsServiceImpl;
import com.ISA.service.implementation.AdventureNotFreeTermsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventureNotFreeTerms")
public class AdventureNotFreeTermsController {

    @Autowired
    private AdventureNotFreeTermsServiceImpl adventureNotFreeTermsService;

    @GetMapping(path = "/{adventureId}")
    public ResponseEntity<?> getAll(@PathVariable Long adventureId) {
        List<AdventureNotFreeTerms> adventureNotFreeTerms = adventureNotFreeTermsService.getAll(adventureId);

        return new ResponseEntity<>(adventureNotFreeTerms, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureNotFreeTermsDTO dto) {
        AdventureNotFreeTerms adventureNotFreeTerms = adventureNotFreeTermsService.add(dto);

        return new ResponseEntity<>(adventureNotFreeTerms, HttpStatus.OK);
    }
}
