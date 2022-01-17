package com.ISA.repository;

import com.ISA.domain.model.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {
    List<Subscriptions> findAllByClientTypeAndIsSubscribed(String type, boolean isSubscribed);
    List<Subscriptions> findAllByClientIdAndIsSubscribed(Long clientId, boolean isSubscribed);
    Subscriptions findByClientIdAndIsSubscribed(Long clientId, boolean isSubscribed);
}
