package com.ISA.controller;

import com.ISA.domain.dto.AdventureComplaintsDTO;
import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.service.definition.AdventureComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
