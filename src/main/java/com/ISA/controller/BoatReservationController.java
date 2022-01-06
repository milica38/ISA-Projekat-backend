package com.ISA.controller;

import com.ISA.domain.dto.BoatReservationDTO;
import com.ISA.domain.dto.SearchFreeBoatsDTO;
import com.ISA.domain.dto.converters.BoatProfileConverters;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.BoatReservation;
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
    private SearchFreeBoatsService freeBoatsService;

    @Autowired
    private BoatReservationService reservationService;

    @PostMapping()
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
}
