package com.ISA.controller;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.dto.BoatComplaintsDTO;
import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.BoatComplaints;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.service.definition.HomeComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homeComplaints")
public class HomeComplaintsController {

    @Autowired
    private HomeComplaintsService homeComplaintsService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeComplaintsDTO dto) {
        HomeComplaints complaints = homeComplaintsService.add(dto);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
    @GetMapping(path = "/getAllHomeComplaints")
    public ResponseEntity<?> getAllHomeComplaints(){
        List<HomeComplaints> complaints = homeComplaintsService.getAllHomeComplaints();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping(path = "/allComplaintsWithoutResponse")
    public ResponseEntity<?> getAllComplaintsByComplaintResponse() {
        return new ResponseEntity<>(homeComplaintsService.getAllComplaintsByComplaintResponse(), HttpStatus.OK);
    }

    @PutMapping(path = "/responseToHomeComplaint")
    public ResponseEntity<?> responseToComplaint( @RequestBody HomeComplaintsDTO dto) {
        HomeComplaints hc = homeComplaintsService.responseToComplaint(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
