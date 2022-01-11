package com.ISA.controller;

import com.ISA.domain.dto.HomeHistoryReservationDTO;
import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.HomeReservation;
import com.ISA.service.definition.HomeReservationService;
import com.ISA.service.definition.SearchFreeHomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(path = "/myReservations")
    public ResponseEntity<?> getMyReservations()
    {
        List<HomeReservation> reservations = homeReservationService.getMyReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        boolean delete = homeReservationService.cancel(id);

        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @GetMapping(path = "/getHousesOnAction")
    public ResponseEntity<?> getAllHousesOnAction(){
        List<HomeFreeTerms> actions = homeReservationService.getAllHousesOnAction();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @PostMapping(path = "/myReservationsForMyHouses")
    public ResponseEntity<?> getReservationsForMyHouses(@RequestBody HomeHistoryReservationDTO dto)
    {
        List<HomeReservation> reservations = homeReservationService.getAllReservationsForMyHouses(dto);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllReservations")
    public ResponseEntity<?> getAllReservations(){
        List<HomeReservation> actions = homeReservationService.getAll();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }
}
