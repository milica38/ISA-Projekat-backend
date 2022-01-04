package com.ISA.controller;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.dto.converters.BoatProfileConverters;
import com.ISA.domain.model.BoatProfile;
import com.ISA.service.definition.BoatProfileService;
import com.ISA.service.implementation.BoatProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boats")
public class BoatProfileController {

    @Autowired
    private BoatProfileServiceImpl boatProfileService;

    @GetMapping(path = "/boat-profiles")
    public ResponseEntity<?> getAll() {
        List<BoatProfile> boatProfiles = boatProfileService.getAllNotDeleted();

        return new ResponseEntity<>(BoatProfileConverters.modelsToDTOs(boatProfiles), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        BoatProfile bp = boatProfileService.get(id);

        return new ResponseEntity<>(bp, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody BoatProfileDTO dto) {
        BoatProfile bp = boatProfileService.add(dto);

        return new ResponseEntity<>(BoatProfileConverters.modelToDTO(bp), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody BoatProfileDTO dto) {
        BoatProfile bp = boatProfileService.edit(id, dto);

        return new ResponseEntity<>(bp, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = boatProfileService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getMyBoats() {
        List<BoatProfile> result = boatProfileService.getMyNotDeletedBoats();

        return new ResponseEntity<>(BoatProfileConverters.modelsToDTOs(result), HttpStatus.OK);
    }

}
