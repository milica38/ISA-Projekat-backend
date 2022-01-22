package com.ISA.repository;

import com.ISA.domain.model.BoatSubscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatSubscriptionsRepository extends JpaRepository<BoatSubscriptions, Long> {
    List<BoatSubscriptions> findAllByClientTypeAndIsSubscribed(String type, boolean isSubscribed);
    List<BoatSubscriptions> findAllByClientIdAndIsSubscribed(Long clientId, boolean isSubscribed);
}
