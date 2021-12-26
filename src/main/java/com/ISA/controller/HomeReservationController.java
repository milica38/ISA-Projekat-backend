package com.ISA.controller;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.HomeReservation;
import com.ISA.service.definition.HomeReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/homeReservations")
public class HomeReservationController {

    @Autowired
    private HomeReservationService homeReservationService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeReservationDTO dto) {
        HomeReservation reservation = homeReservationService.add(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
