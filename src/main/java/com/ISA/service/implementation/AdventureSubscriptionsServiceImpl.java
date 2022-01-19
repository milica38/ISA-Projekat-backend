package com.ISA.service.implementation;

import com.ISA.domain.dto.AdventureSubscriptionsDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.AdventureProfileRepository;
import com.ISA.repository.AdventureSubscriptionsRepository;
import com.ISA.service.definition.AdventureSubscriptionsService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureSubscriptionsServiceImpl  implements AdventureSubscriptionsService {

    @Autowired
    private AdventureSubscriptionsRepository subscriptionsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdventureProfileRepository adventureProfileRepository;

    @Override
    public AdventureSubscriptions subscribeUser(Long id, AdventureSubscriptionsDTO dto) {
        AdventureProfile adventureProfile = adventureProfileRepository.findById(id).get();
        User currentUser = userService.getCurrentUser();
        if(!canClientSubscribe(adventureProfile.getId(), currentUser.getId())){
            return null;
        }

        AdventureSubscriptions subscription = new AdventureSubscriptions();
        subscription.setSubscribed(true);
        subscription.setClient(currentUser);
        subscription.setAdventureProfile(adventureProfile);

        return subscriptionsRepository.save(subscription);
    }

    @Override
    public boolean unSubscribeUser(Long id) {
        AdventureSubscriptions subscription = subscriptionsRepository.findById(id).get();
        subscription.setSubscribed(false);
        subscriptionsRepository.save(subscription);
        return true;
    }

    @Override
    public List<AdventureSubscriptions> getMySubscriptions() {
        return subscriptionsRepository.findAllByClientIdAndIsSubscribed(userService.getCurrentUser().getId(), true);
    }


    public boolean canClientSubscribe(Long adventureId, Long clientId) {
        List<AdventureSubscriptions> subscriptions = subscriptionsRepository.findAll();
        for(AdventureSubscriptions subscription: subscriptions){
            if(subscription.getAdventureProfile().getId().equals(adventureId) && subscription.isSubscribed() == true && subscription.getClient().getId().equals(clientId)){
                return false;
            }
        }
        return true;
    }
}
