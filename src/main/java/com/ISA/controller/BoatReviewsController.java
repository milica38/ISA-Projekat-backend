package com.ISA.controller;

import com.ISA.domain.dto.BoatReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.BoatReviews;
import com.ISA.service.definition.BoatReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatReviews")
public class BoatReviewsController {

    @Autowired
    private BoatReviewsService boatReviewsService;

    @PreAuthorize("hasAuthority('Boat owner')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody BoatReviewsDTO dto) {
        BoatReviews reviews = boatReviewsService.add(dto);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllBoatReviews")
    public ResponseEntity<?> getAllBoatReviews(){
        List<BoatReviews> reviews = boatReviewsService.getAllBoatReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/withoutOnePenalty")
    public ResponseEntity<?> getAllReviewsByOnePenalty() {
        return new ResponseEntity<>(boatReviewsService.getAllReviewsByOnePenalty() , HttpStatus.OK);
    }

    @PostMapping(path = "/strike/{id}")
    public ResponseEntity<?> strikeClient(@PathVariable Long id){
        boatReviewsService.strikeOnePenalty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/withoutOnePenaltyAndBadComment")
    public ResponseEntity<?> getAllReviewsByOnePenaltyAndBadComment() {
        return new ResponseEntity<>(boatReviewsService.getAllReviewsByOnePenaltyAndBadComment() , HttpStatus.OK);
    }
}
