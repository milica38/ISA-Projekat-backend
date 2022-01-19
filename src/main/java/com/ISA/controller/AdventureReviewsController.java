package com.ISA.controller;

import com.ISA.domain.dto.AdventureReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.service.definition.AdventureReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
