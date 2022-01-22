package com.ISA.service.implementation;

import com.ISA.domain.dto.BoatSubscriptionsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.BoatProfileRepository;
import com.ISA.repository.BoatSubscriptionsRepository;
import com.ISA.service.definition.BoatSubscriptionsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatSubscriptionsServiceImpl implements BoatSubscriptionsService {

    @Autowired
    private BoatSubscriptionsRepository subscriptionsRepository;

    @Autowired
    private BoatProfileRepository boatProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    public BoatSubscriptions subscribeUser(Long id, BoatSubscriptionsDTO dto) {
        BoatProfile boatProfile = boatProfileRepository.findById(id).get();
        User currentUser = userService.getCurrentUser();
        if(!canClientSubscribe(boatProfile.getId(), currentUser.getId())){
            return null;
        }

        BoatSubscriptions subscription = new BoatSubscriptions();
        subscription.setSubscribed(true);
        subscription.setClient(currentUser);
        subscription.setBoatProfile(boatProfile);

        return subscriptionsRepository.save(subscription);
    }

    @Override
    public boolean unSubscribeUser(Long id) {
        BoatSubscriptions subscription = subscriptionsRepository.findById(id).get();
        subscription.setSubscribed(false);
        subscriptionsRepository.save(subscription);
        return true;
    }

    @Override
    public List<BoatSubscriptions> getMySubscriptions() {
        return subscriptionsRepository.findAllByClientIdAndIsSubscribed(userService.getCurrentUser().getId(), true);
    }

    public boolean canClientSubscribe(Long boatId, Long clientId) {
        List<BoatSubscriptions> subscriptions = subscriptionsRepository.findAll();
        for(BoatSubscriptions subscription: subscriptions){
            if(subscription.getBoatProfile().getId().equals(boatId) && subscription.isSubscribed() == true && subscription.getClient().getId().equals(clientId)){
                return false;
            }
        }
        return true;
    }
}
