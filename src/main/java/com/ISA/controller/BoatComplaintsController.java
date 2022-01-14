package com.ISA.controller;

import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.model.BoatComplaints;
import com.ISA.service.definition.BoatComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatComplaints")
public class BoatComplaintsController {

    @Autowired
    private BoatComplaintsService boatComplaintsService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody BoatComplaintsDTO dto) {
        BoatComplaints complaints = boatComplaintsService.add(dto);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
}
