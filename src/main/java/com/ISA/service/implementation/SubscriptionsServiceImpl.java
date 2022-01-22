package com.ISA.service.implementation;

import com.ISA.domain.dto.SubscriptionsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.SubscriptionsRepository;
import com.ISA.service.definition.SubscriptionService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionsServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Autowired
    private UserService userService;

    @Override
    public Subscriptions subscribeUser(Long id, SubscriptionsDTO dto) {

        HomeProfile homeProfile = homeProfileRepository.findById(id).get();
        User currentUser = userService.getCurrentUser();
        if(!canClientSubscribe(homeProfile.getId(), currentUser.getId())){
            return null;
        }

        Subscriptions subscription = new Subscriptions();
        subscription.setSubscribed(true);
        subscription.setClient(currentUser);
        subscription.setHomeProfile(homeProfile);

        return subscriptionsRepository.save(subscription);
    }



    @Override
    public boolean unSubscribeUser(Long id) {
        Subscriptions subscription = subscriptionsRepository.findById(id).get();
        subscription.setSubscribed(false);
        subscriptionsRepository.save(subscription);
        return true;
    }

    @Override
    public List<Subscriptions> getMySubscriptions() {
        return subscriptionsRepository.findAllByClientIdAndIsSubscribed(userService.getCurrentUser().getId(), true);
    }


    public boolean canClientSubscribe(Long houseId, Long clientId) {
        List<Subscriptions> subscriptions = subscriptionsRepository.findAll();
        for(Subscriptions subscription: subscriptions){
            if(subscription.getHomeProfile().getId().equals(houseId) && subscription.isSubscribed() == true && subscription.getClient().getId().equals(clientId)){
                return false;
            }
        }
        return true;
    }
}
