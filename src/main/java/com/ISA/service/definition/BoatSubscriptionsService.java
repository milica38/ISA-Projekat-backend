package com.ISA.service.definition;

import com.ISA.domain.dto.BoatSubscriptionsDTO;
import com.ISA.domain.model.BoatSubscriptions;

import java.util.List;

public interface BoatSubscriptionsService {
    BoatSubscriptions subscribeUser(Long id, BoatSubscriptionsDTO dto);
    boolean unSubscribeUser(Long id);
    List<BoatSubscriptions> getMySubscriptions();
}
