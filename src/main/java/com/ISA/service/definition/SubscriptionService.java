package com.ISA.service.definition;

import com.ISA.domain.dto.SubscriptionsDTO;
import com.ISA.domain.model.Subscriptions;

import java.util.List;

public interface SubscriptionService {
    Subscriptions subscribeUser(Long id, SubscriptionsDTO dto);
    boolean unSubscribeUser(Long id);
    List<Subscriptions> getMySubscriptions();

}
