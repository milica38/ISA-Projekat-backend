package com.ISA.repository;

import com.ISA.domain.model.AdventureSubscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdventureSubscriptionsRepository extends JpaRepository<AdventureSubscriptions, Long> {

    List<AdventureSubscriptions> findAllByClientTypeAndIsSubscribed(String type, boolean isSubscribed);
    List<AdventureSubscriptions> findAllByClientIdAndIsSubscribed(Long clientId, boolean isSubscribed);
}
