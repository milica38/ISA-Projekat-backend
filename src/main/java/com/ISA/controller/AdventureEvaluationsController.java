package com.ISA.controller;

import com.ISA.domain.dto.AdventureEvaluationsDTO;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.service.definition.AdventureEvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventureEvaluations")
public class AdventureEvaluationsController {

    @Autowired
    private AdventureEvaluationsService adventureEvaluationsService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureEvaluationsDTO dto) {
        AdventureEvaluations evaluations = adventureEvaluationsService.add(dto);

        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

}
