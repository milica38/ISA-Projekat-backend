package com.ISA.service.definition;

import com.ISA.domain.dto.AdventureSubscriptionsDTO;
import com.ISA.domain.model.AdventureSubscriptions;

import java.util.List;

public interface AdventureSubscriptionsService {

    AdventureSubscriptions subscribeUser(Long id, AdventureSubscriptionsDTO dto);
    boolean unSubscribeUser(Long id);
    List<AdventureSubscriptions> getMySubscriptions();
}
