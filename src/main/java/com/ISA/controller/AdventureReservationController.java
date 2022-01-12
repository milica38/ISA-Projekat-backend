package com.ISA.controller;

import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.dto.SearchFreeAdventuresDTO;
import com.ISA.domain.dto.converters.AdventureProfileConverters;
import com.ISA.domain.model.*;
import com.ISA.service.definition.AdventureReservationService;
import com.ISA.service.definition.SearchFreeAdventuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventuresReservation")


public class AdventureReservationController {

    @Autowired
    private AdventureReservationService reservationService;

    @Autowired
    private SearchFreeAdventuresService freeTermsAdventuresService;


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

    @GetMapping(path = "/myReservations")
    public ResponseEntity<?> getMyReservations()
    {
        List<AdventureReservation> reservations = reservationService.getMyReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/getAdventuresOnAction")
    public ResponseEntity<?> getAllAdventuresOnAction(){
        List<AdventureFreeTerms> actions = reservationService.getAllAdventuresOnAction();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        boolean delete = reservationService.cancel(id);

        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        AdventureReservation reservation = reservationService.get(id);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }



}
