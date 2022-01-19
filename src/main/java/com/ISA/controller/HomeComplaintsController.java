package com.ISA.controller;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.service.definition.HomeComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


}
