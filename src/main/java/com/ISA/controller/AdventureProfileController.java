package com.ISA.controller;


import com.ISA.domain.dto.AdventureProfileDTO;
import com.ISA.domain.dto.converters.AdventureProfileConverters;
import com.ISA.domain.model.AdventureProfile;
import com.ISA.service.definition.AdventureProfileService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/adventures")
public class AdventureProfileController {

    @Autowired
    private AdventureProfileService adventureProfileService;

    @GetMapping(path = "/adventure-profiles")
    public ResponseEntity<?> getAll() {
        List<AdventureProfile> adventureProfiles = adventureProfileService.getAllNotDeleted();

        return new ResponseEntity<>(AdventureProfileConverters.modelsToDTOs(adventureProfiles), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        AdventureProfile ap = adventureProfileService.get(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AdventureProfileDTO dto) {
        AdventureProfile ap = adventureProfileService.add(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody AdventureProfileDTO dto) {
        AdventureProfile ap = adventureProfileService.edit(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = adventureProfileService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/filterAdventures")
    public ResponseEntity<?> filterAdventures(@RequestBody AdventureProfileDTO dto){
        List<AdventureProfile> profiles = adventureProfileService.filterAdventures(dto);
        return new ResponseEntity<>(AdventureProfileConverters.modelsToDTOs(profiles), HttpStatus.OK);
    }
}
