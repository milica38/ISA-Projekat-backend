package com.ISA.service.definition;

import com.ISA.domain.dto.SubscriptionsDTO;
import com.ISA.domain.model.Subscriptions;

public interface SubscriptionService {
    Subscriptions subscribeUser(Long id, SubscriptionsDTO dto);

}
