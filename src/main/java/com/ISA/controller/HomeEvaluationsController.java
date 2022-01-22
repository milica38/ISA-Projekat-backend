package com.ISA.controller;

import com.ISA.domain.dto.HomeEvaluationsDTO;
import com.ISA.domain.model.AdventureEvaluations;
import com.ISA.domain.model.HomeEvaluations;
import com.ISA.service.definition.HomeEvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homeEvaluations")
public class HomeEvaluationsController {

    @Autowired
    private HomeEvaluationsService homeEvaluationsService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeEvaluationsDTO dto) {
        HomeEvaluations evaluations = homeEvaluationsService.add(dto);
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllHomeEvaluations")
    public ResponseEntity<?> getAllHomeEvaluations(){
        List<HomeEvaluations> evaluations = homeEvaluationsService.getAllHomeEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping(path = "/notApprovedOrDeclined")
    public ResponseEntity<?> getAllEvaluationsApprovedAndDeclined() {
        return new ResponseEntity<>(homeEvaluationsService.getAllEvaluationsByApprovedAndDeclined() , HttpStatus.OK);
    }

    @PostMapping(path = "/approve/{id}")
    public ResponseEntity<?> approveEvaluation(@PathVariable Long id){
        homeEvaluationsService.evaluationApproved(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/decline/{id}")
    public ResponseEntity<?> declineEvaluation(@PathVariable Long id){
        homeEvaluationsService.evaluationDeclined(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
