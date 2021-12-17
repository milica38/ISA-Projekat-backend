package com.ISA.controller;

import com.ISA.domain.dto.AdventureReservationDTO;
import com.ISA.domain.model.AdventureReservation;
import com.ISA.service.definition.AdventureReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adventuresReservation")


public class AdventureReservationController {

    @Autowired
    private AdventureReservationService adventureReservationService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<AdventureReservation> adventureReservation = adventureReservationService.getAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        AdventureReservation ar = adventureReservationService.get(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureReservationDTO dto) {
        AdventureReservation ar = adventureReservationService.add(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody AdventureReservationDTO dto) {
        AdventureReservation ar = adventureReservationService.edit(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = adventureReservationService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
