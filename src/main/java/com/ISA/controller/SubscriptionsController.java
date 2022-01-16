package com.ISA.controller;

import com.ISA.domain.dto.SubscriptionsDTO;
import com.ISA.domain.model.Subscriptions;
import com.ISA.service.definition.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/subscriptions")
public class SubscriptionsController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> subscribeUser(@PathVariable Long id, @RequestBody SubscriptionsDTO dto) {
        Subscriptions subscription = subscriptionService.subscribeUser(id, dto);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
