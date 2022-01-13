package com.ISA.controller;

import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.BoatEvaluations;
import com.ISA.service.definition.BoatEvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatEvaluations")
public class BoatEvaluationsController {

    @Autowired
    private BoatEvaluationsService boatEvaluationsService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody BoatEvaluationsDTO dto) {

        BoatEvaluations evaluations = boatEvaluationsService.add(dto);

        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
}
