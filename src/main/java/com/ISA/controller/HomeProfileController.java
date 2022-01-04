package com.ISA.controller;

import com.ISA.domain.dto.HomeProfileDTO;
import com.ISA.domain.dto.converters.HomeProfileConverters;
import com.ISA.domain.model.HomeProfile;
import com.ISA.service.definition.HomeProfileService;
import com.ISA.service.implementation.HomeProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/homes")
public class HomeProfileController {

    @Autowired
    private HomeProfileServiceImpl homeProfileService;

    @GetMapping(path = "/home-profiles")
    public ResponseEntity<?> getAll() {
        List<HomeProfile> homeProfiles = homeProfileService.getAllNotDeleted();

        return new ResponseEntity<>(HomeProfileConverters.modelsToDTOs(homeProfiles), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        HomeProfile hp = homeProfileService.get(id);

        return new ResponseEntity<>(HomeProfileConverters.modelToDTO(hp), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody HomeProfileDTO dto) {
        HomeProfile hp = homeProfileService.add(dto);

        return new ResponseEntity<>(HomeProfileConverters.modelToDTO(hp), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody HomeProfileDTO dto) {
        HomeProfile hp = homeProfileService.edit(id, dto);

        return new ResponseEntity<>(hp, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = homeProfileService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getMyHouses() {

        List<HomeProfile> result = homeProfileService.getMyNotDeletedHouses();

        return new ResponseEntity<>(HomeProfileConverters.modelsToDTOs(result), HttpStatus.OK);
    }

    @PostMapping(path = "/filterHomes")
    public ResponseEntity<?> filterHomes(@RequestBody HomeProfileDTO dto){
        List<HomeProfile> profiles = homeProfileService.filterHomes(dto);
        return new ResponseEntity<>(HomeProfileConverters.modelsToDTOs(profiles), HttpStatus.OK);
    }

}
