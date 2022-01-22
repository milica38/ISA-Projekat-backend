package com.ISA.controller;

import com.ISA.domain.dto.BoatEvaluationsDTO;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.BoatEvaluations;
import com.ISA.service.definition.BoatEvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatEvaluations")
public class BoatEvaluationsController {

    @Autowired
    private BoatEvaluationsService boatEvaluationsService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody BoatEvaluationsDTO dto) {
        BoatEvaluations evaluations = boatEvaluationsService.add(dto);
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllBoatEvaluations")
    public ResponseEntity<?> getAllBoatEvaluations(){
        List<BoatEvaluations> evaluations = boatEvaluationsService.getAllBoatEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping(path = "/notApprovedOrDeclined")
    public ResponseEntity<?> getAllEvaluationsApprovedAndDeclined() {
        return new ResponseEntity<>(boatEvaluationsService.getAllEvaluationsByApprovedAndDeclined() , HttpStatus.OK);
    }

    @PostMapping(path = "/approve/{id}")
    public ResponseEntity<?> approveEvaluation(@PathVariable Long id){
        boatEvaluationsService.evaluationApproved(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/decline/{id}")
    public ResponseEntity<?> declineEvaluation(@PathVariable Long id){
        boatEvaluationsService.evaluationDeclined(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
