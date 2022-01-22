package com.ISA.controller;

import com.ISA.domain.dto.HomeReviewsDTO;
import com.ISA.domain.model.AdventureReviews;
import com.ISA.domain.model.HomeReviews;
import com.ISA.service.definition.HomeReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homeReviews")
public class HomeReviewsController {

    @Autowired
    private HomeReviewsService homeReviewsService;

    @PreAuthorize("hasAuthority('House owner')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody HomeReviewsDTO dto) {
        HomeReviews reviews = homeReviewsService.add(dto);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllHomeReviews")
    public ResponseEntity<?> getAllHomeReviews(){
        List<HomeReviews> reviews = homeReviewsService.getAllHomeReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(path = "/withoutOnePenalty")
    public ResponseEntity<?> getAllReviewsByOnePenalty() {
        return new ResponseEntity<>(homeReviewsService.getAllReviewsByOnePenalty() , HttpStatus.OK);
    }

    @PostMapping(path = "/strike/{id}")
    public ResponseEntity<?> strikeClient(@PathVariable Long id){
        homeReviewsService.strikeOnePenalty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
