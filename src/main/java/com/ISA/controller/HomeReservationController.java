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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homeReservations")
public class HomeReservationController {

    @Autowired
    private HomeReservationService homeReservationService;

    @Autowired
    private SearchFreeHomesService freeHomesService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeReservationDTO dto) throws Exception {
        HomeReservation reservation = null;
        try {
            reservation = homeReservationService.add(dto);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner')")
    @PostMapping(path = "/owner")
    public ResponseEntity<?> addByOwner(@RequestBody HomeReservationDTO dto) throws Exception{
        HomeReservation reservation = homeReservationService.addByOwner(dto, dto.getClientId());

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping(path = "/searchFree")
    public ResponseEntity<?> searchFree(@RequestBody SearchFreeHomesDTO dto) {
        List<HomeProfile> homes = freeHomesService.findAllFree(dto);
        return new ResponseEntity<>(HomeProfileConverters.modelsToDTOs(homes), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/myReservations")
    public ResponseEntity<?> getMyReservations() {
        List<HomeReservation> reservations = homeReservationService.getMyReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        boolean delete = homeReservationService.cancel(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/getHousesOnAction")
    public ResponseEntity<?> getAllHousesOnAction(){
        List<HomeFreeTerms> actions = homeReservationService.getAllHousesOnAction();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner')")
    @PostMapping(path = "/myReservationsForMyHouses")
    public ResponseEntity<?> getReservationsForMyHouses(@RequestBody HomeHistoryReservationDTO dto)
    {
        List<HomeReservation> reservations = homeReservationService.getAllReservationsForMyHouses(dto);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner')")
    @PostMapping(path = "/myTodayReservationsForMyHouses")
    public ResponseEntity<?> getTodayReservationsForMyHouses(@RequestBody HomeHistoryReservationDTO dto)
    {
        List<HomeReservation> reservations = homeReservationService.getAllTodayReservationsForMyHouses(dto);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner')")
    @PostMapping(path = "/myHistoryReservationsForMyHouses")
    public ResponseEntity<?> getHistoryReservationsForMyHouses(@RequestBody HomeHistoryReservationDTO dto)
    {
        List<HomeReservation> reservations = homeReservationService.getAllHistoryReservationsForMyHouses(dto);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner')")
    @PostMapping(path = "/myReservationsForCharts")
    public ResponseEntity<?> getReservationsForCharts(@RequestBody HomeHistoryReservationDTO dto)
    {
        List<HomeReservation> reservations = homeReservationService.getAllReservationsForCharts(dto);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('PredefinedAdmin')")
    @PostMapping(path = "/homeReservationsForCharts")
    public ResponseEntity<?> getAllHomeReservationsForCharts(@RequestBody HomeHistoryReservationDTO dto)
    {
        List<HomeReservation> reservations = homeReservationService.getAllHomeReservationsForCharts(dto);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
  
    @PreAuthorize("hasAuthority('Client') or hasAuthority('House owner')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        HomeReservation reservation = homeReservationService.get(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('House owner')")
    @GetMapping(path = "/getAllReservations/{houseId}/{ownerId}")
    public ResponseEntity<?> getAllReservations(@PathVariable Long houseId, @PathVariable Long ownerId){
        List<HomeReservation> actions = homeReservationService.getAllReservations(ownerId, houseId);
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/myFinishedReservations")
    public ResponseEntity<?> getMyFinishedReservations() {
        List<HomeReservation> reservations = homeReservationService.getMyFinishedReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/myUpcomingReservations")
    public ResponseEntity<?> getMyUpcomingReservations() {
        List<HomeReservation> reservations = homeReservationService.getMyUpcomingReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/myInProgressReservations")
    public ResponseEntity<?> getMyInProgressReservations() {
        List<HomeReservation> reservations = homeReservationService.getMyInProgressReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

}
