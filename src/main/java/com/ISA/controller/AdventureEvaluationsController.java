package com.ISA.controller;

import com.ISA.domain.dto.AdventureEvaluationsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.service.definition.AdventureEvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventureEvaluations")
public class AdventureEvaluationsController {

    @Autowired
    private AdventureEvaluationsService adventureEvaluationsService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureEvaluationsDTO dto) {
        AdventureEvaluations evaluations = adventureEvaluationsService.add(dto);

        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllAdventureEvaluations")
    public ResponseEntity<?> getAllAdventureEvaluations(){
        List<AdventureEvaluations> evaluations = adventureEvaluationsService.getAllAdventureEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping(path = "/notApprovedOrDeclined")
   public ResponseEntity<?> getAllEvaluationsApprovedAndDeclined() {
        return new ResponseEntity<>(adventureEvaluationsService.getAllEvaluationsByApprovedAndDeclined() , HttpStatus.OK);
    }

    @PostMapping(path = "/approve/{id}")
    public ResponseEntity<?> approveEvaluation(@PathVariable Long id){

        adventureEvaluationsService.evaluationApproved(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/decline/{id}")
    public ResponseEntity<?> declineEvaluation(@PathVariable Long id){
        adventureEvaluationsService.evaluationDeclined(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
