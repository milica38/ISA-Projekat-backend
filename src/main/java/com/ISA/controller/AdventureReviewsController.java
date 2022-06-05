package com.ISA.controller;

import com.ISA.domain.dto.AdventureReviewsDTO;
import com.ISA.domain.model.AdventureComplaints;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.service.definition.AdventureReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventureReviews")
public class AdventureReviewsController {

    @Autowired
    private AdventureReviewsService adventureReviewsService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AdventureReviewsDTO dto) {
        AdventureReviews reviews = adventureReviewsService.add(dto);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllAdventureReviews")
    public ResponseEntity<?> getAllAdventureReviews(){
        List<AdventureReviews> reviews = adventureReviewsService.getAllAdventureReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/withoutOnePenalty")
    public ResponseEntity<?> getAllReviewsByOnePenalty() {
        return new ResponseEntity<>(adventureReviewsService.getAllReviewsByOnePenalty() , HttpStatus.OK);
    }

    @GetMapping(path = "/withoutOnePenaltyAndBadComment")
    public ResponseEntity<?> getAllReviewsByOnePenaltyAndBadComment() {
        return new ResponseEntity<>(adventureReviewsService.getAllReviewsByOnePenaltyAndBadComment() , HttpStatus.OK);
    }

    @PostMapping(path = "/strike/{id}")
    public ResponseEntity<?> strikeClient(@PathVariable Long id){
        adventureReviewsService.strikeOnePenalty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
