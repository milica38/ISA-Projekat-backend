package com.ISA.controller;

import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.dto.SearchFreeBoatsDTO;
import com.ISA.domain.dto.converters.BoatProfileConverters;
import com.ISA.domain.model.*;
import com.ISA.service.definition.BoatReservationService;
import com.ISA.service.definition.SearchFreeBoatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatReservations")
public class BoatReservationController {

    @Autowired
    private BoatReservationService reservationService;

    @Autowired
    private SearchFreeBoatsService freeBoatsService;


    @PostMapping(path = "/book")
    public ResponseEntity<?> add(@RequestBody BoatReservationDTO dto) {
        BoatReservation reservation = reservationService.add(dto);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping(path = "/searchFree")
    public ResponseEntity<?> searchFree(@RequestBody SearchFreeBoatsDTO dto)
    {
        List<BoatProfile> boats = freeBoatsService.findAllFree(dto);

        return new ResponseEntity<>(BoatProfileConverters.modelsToDTOs(boats), HttpStatus.OK);
    }

    @GetMapping(path = "/myReservations")
    public ResponseEntity<?> getMyReservations()
    {
        List<BoatReservation> reservations = reservationService.getMyReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/getBoatsOnAction")
    public ResponseEntity<?> getAllBoatsOnAction(){
        List<BoatFreeTerms> actions = reservationService.getAllBoatsOnAction();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        boolean delete = reservationService.cancel(id);

        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        BoatReservation reservation = reservationService.get(id);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
