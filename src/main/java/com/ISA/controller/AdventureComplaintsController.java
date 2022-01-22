package com.ISA.controller;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.AdventureReservation;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.service.definition.AdventureComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventureComplaints")
public class AdventureComplaintsController {

    @Autowired
    private AdventureComplaintsService adventureComplaintsService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureComplaintsDTO dto) {
        AdventureComplaints complaints = adventureComplaintsService.add(dto);

        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }


    @GetMapping(path = "/getAllAdventureComplaints")
    public ResponseEntity<?> getAllAdventureComplaints(){
        List<AdventureComplaints> complaints = adventureComplaintsService.getAllAdventureComplaints();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping(path = "/allComplaintsWithoutResponse")
    public ResponseEntity<?> getAllComplaintsByComplaintResponse() {
        return new ResponseEntity<>(adventureComplaintsService.getAllComplaintsByComplaintResponse(), HttpStatus.OK);
    }
}
