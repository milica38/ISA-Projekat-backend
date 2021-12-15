package com.ISA.controller;

import com.ISA.domain.dto.BoatProfileDTO;
import com.ISA.domain.model.BoatProfile;
import com.ISA.service.definition.BoatProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boats")
public class BoatProfileController {

    @Autowired
    private BoatProfileService boatProfileService;

    @GetMapping(path = "/boat-profiles")
    public ResponseEntity<?> getAll() {
        List<BoatProfile> boatProfiles = boatProfileService.getAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        BoatProfile bp = boatProfileService.get(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody BoatProfileDTO dto) {
        BoatProfile bp = boatProfileService.add(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody BoatProfileDTO dto) {
        BoatProfile bp = boatProfileService.edit(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = boatProfileService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
