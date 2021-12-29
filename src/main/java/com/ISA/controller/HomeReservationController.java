package com.ISA.controller;

import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;
import com.ISA.service.definition.HomeReservationService;
import com.ISA.service.definition.SearchFreeHomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/homeReservations")
public class HomeReservationController {

    @Autowired
    private HomeReservationService homeReservationService;

    @Autowired
    private SearchFreeHomesService freeHomesService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeReservationDTO dto) {
        HomeReservation reservation = homeReservationService.add(dto);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping(path = "/searchFree")
    public ResponseEntity<?> searchFree(@RequestBody SearchFreeHomesDTO dto)
    {
        List<HomeProfile> homes = freeHomesService.findAllFree(dto);

        return new ResponseEntity<>(HomeProfileConverters.modelsToDTOs(homes), HttpStatus.OK);
    }


}
