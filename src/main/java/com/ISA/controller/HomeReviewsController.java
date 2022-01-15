package com.ISA.controller;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.HomeReviews;
import com.ISA.service.definition.HomeReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homeReviews")
public class HomeReviewsController {

    @Autowired
    private HomeReviewsService homeReviewsService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody HomeReviewsDTO dto) {
        HomeReviews reviews = homeReviewsService.add(dto);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
