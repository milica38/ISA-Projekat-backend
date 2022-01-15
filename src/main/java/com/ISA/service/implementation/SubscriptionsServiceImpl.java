package com.ISA.service.implementation;

import com.ISA.domain.dto.SubscriptionsDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.Subscriptions;
import com.ISA.domain.model.User;
import com.ISA.repository.HomeFreeTermsRepository;
import com.ISA.repository.HomeProfileRepository;
import com.ISA.repository.SubscriptionsRepository;
import com.ISA.service.definition.SubscriptionService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //HomeFreeTerms action = homeFreeTermsRepository.findByIdAndIsAction(id, true);

        HomeProfile homeProfile = homeProfileRepository.findById(id).get();
        User currentUser = userService.getCurrentUser();

        Subscriptions subscription = new Subscriptions();
        subscription.setSubscribed(true);
        subscription.setClient(currentUser);
        subscription.setHomeProfile(homeProfile);

        return subscriptionsRepository.save(subscription);
    }
}
