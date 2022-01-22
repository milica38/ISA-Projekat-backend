package com.ISA.controller;

import com.ISA.domain.dto.AdventureSubscriptionsDTO;
import com.ISA.domain.dto.BoatSubscriptionsDTO;
import com.ISA.domain.dto.SubscriptionsDTO;
import com.ISA.domain.model.*;
import com.ISA.service.definition.AdventureSubscriptionsService;
import com.ISA.service.definition.BoatSubscriptionsService;
import com.ISA.service.definition.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/subscriptions")
public class SubscriptionsController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private BoatSubscriptionsService boatSubscriptionsService;

    @Autowired
    private AdventureSubscriptionsService adventureSubscriptionsService;

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping(path = "/subscribe/{id}")
    public ResponseEntity<?> subscribeUser(@PathVariable Long id, @RequestBody SubscriptionsDTO dto) {
        Subscriptions subscription = subscriptionService.subscribeUser(id, dto);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping(path = "/unsubscribe/{id}")
    public ResponseEntity<?> unSubscribeUser(@PathVariable Long id, @RequestBody SubscriptionsDTO dto) {
        subscriptionService.unSubscribeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/mySubscribedHomeProfiles")
    public ResponseEntity<?> getMySubscribedProfiles() {
        List<Subscriptions> subscriptions = subscriptionService.getMySubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping(path = "/subscribeBoat/{id}")
    public ResponseEntity<?> subscribeUserOnBoats(@PathVariable Long id, @RequestBody BoatSubscriptionsDTO dto) {
        BoatSubscriptions subscription = boatSubscriptionsService.subscribeUser(id, dto);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping(path = "/unsubscribeBoat/{id}")
    public ResponseEntity<?> unSubscribeUserOnBoats(@PathVariable Long id, @RequestBody BoatSubscriptionsDTO dto) {
        boatSubscriptionsService.unSubscribeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/mySubscribedBoatProfiles")
    public ResponseEntity<?> getMySubscribedBoatProfiles() {
        List<BoatSubscriptions> subscriptions = boatSubscriptionsService.getMySubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping(path = "/subscribeAdventure/{id}")
    public ResponseEntity<?> subscribeOnAdventureUser(@PathVariable Long id, @RequestBody AdventureSubscriptionsDTO dto) {
        AdventureSubscriptions subscription = adventureSubscriptionsService.subscribeUser(id, dto);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping(path = "/unsubscribeAdventure/{id}")
    public ResponseEntity<?> unSubscribeUserFromAdventure(@PathVariable Long id, @RequestBody AdventureSubscriptionsDTO dto) {
        adventureSubscriptionsService.unSubscribeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping(path = "/mySubscribedAdventureProfiles")
    public ResponseEntity<?> getMySubscribedAdventureProfiles() {
        List<AdventureSubscriptions> subscriptions = adventureSubscriptionsService.getMySubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}
