package com.ISA.controller;

import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.service.implementation.HomeFreeTermsServiceImpl;
import com.ISA.service.implementation.HomeProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/hometerms")
public class HomeFreeTermsController {

    @Autowired
    private HomeFreeTermsServiceImpl homeFreeTermsService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getAll() {
        List<HomeFreeTerms> homeFreeTerms = homeFreeTermsService.getAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
