package com.ISA.controller;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.model.HomeProfile;
import com.ISA.service.definition.HomeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/homes")
public class HomeProfileController {

    @Autowired
    private HomeProfileService homeProfileService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<HomeProfile> homeProfiles = homeProfileService.getAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        HomeProfile hp = homeProfileService.get(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeProfileDTO dto) {
        HomeProfile hp = homeProfileService.add(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody HomeProfileDTO dto) {
        HomeProfile hp = homeProfileService.edit(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = homeProfileService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
