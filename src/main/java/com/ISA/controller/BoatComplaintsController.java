package com.ISA.controller;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.BoatComplaints;
import com.ISA.service.definition.BoatComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping(path = "/getAllBoatComplaints")
    public ResponseEntity<?> getAllBoatComplaints(){
        List<BoatComplaints> complaints = boatComplaintsService.getAllBoatComplaints();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping(path = "/allComplaintsWithoutResponse")
    public ResponseEntity<?> getAllComplaintsByComplaintResponse() {
        return new ResponseEntity<>(boatComplaintsService.getAllComplaintsByComplaintResponse(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('PredefinedAdmin')")
    @PutMapping(path = "/responseToBoatComplaint")
    public ResponseEntity<?> responseToComplaint( @RequestBody BoatComplaintsDTO dto) throws Exception{
        BoatComplaints bc = null;
        try {
            bc = boatComplaintsService.responseToComplaint(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bc,HttpStatus.OK);
    }
}
