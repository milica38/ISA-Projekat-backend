package com.ISA.controller;

import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.dto.SearchFreeAdventuresDTO;
import com.ISA.domain.dto.converters.AdventureProfileConverters;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.AdventureReservation;
import com.ISA.service.definition.AdventureReservationService;
import com.ISA.service.definition.SearchFreeTermsAdventuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adventuresReservation")


public class AdventureReservationController {

    @Autowired
    private AdventureReservationService reservationService;

    @Autowired
    private SearchFreeTermsAdventuresService freeTermsAdventuresService;




    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureReservationDTO dto) {
        AdventureReservation reservation = reservationService.add(dto);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }


    @PostMapping(path = "/searchFree")
    public ResponseEntity<?> searchFree(@RequestBody SearchFreeAdventuresDTO dto)
    {
        List<AdventureProfile> adventures = freeTermsAdventuresService.findAllFree(dto);

        return new ResponseEntity<>(AdventureProfileConverters.modelsToDTOs(adventures), HttpStatus.OK);
    }




}
