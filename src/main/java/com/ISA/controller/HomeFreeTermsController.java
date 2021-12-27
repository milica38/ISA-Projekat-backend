package com.ISA.controller;

import com.ISA.domain.dto.HomeFreeTermsDTO;
import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.service.implementation.HomeFreeTermsServiceImpl;
import com.ISA.service.implementation.HomeProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/hometerms")
public class HomeFreeTermsController {

    @Autowired
    private HomeFreeTermsServiceImpl homeFreeTermsService;

    @GetMapping(path = "/{houseId}")
    public ResponseEntity<?> getAll(@PathVariable Long houseId) {
        List<HomeFreeTerms> homeFreeTerms = homeFreeTermsService.getAll(houseId);
        return new ResponseEntity<>(homeFreeTerms, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeFreeTermsDTO dto) {
        HomeFreeTerms homeFreeTerms = homeFreeTermsService.add(dto);
        return new ResponseEntity<>(homeFreeTerms,HttpStatus.OK);
    }
}
