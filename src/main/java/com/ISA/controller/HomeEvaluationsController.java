package com.ISA.controller;

import com.ISA.domain.dto.HomeComplaintsDTO;
import com.ISA.domain.dto.HomeEvaluationsDTO;
import com.ISA.domain.model.HomeComplaints;
import com.ISA.domain.model.HomeEvaluations;
import com.ISA.service.definition.HomeComplaintsService;
import com.ISA.service.definition.HomeEvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homeEvaluations")
public class HomeEvaluationsController {

    @Autowired
    private HomeEvaluationsService homeEvaluationsService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeEvaluationsDTO dto) {
        HomeEvaluations evaluations = homeEvaluationsService.add(dto);

        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }



}
