package com.ISA.repository;

import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ISA.domain.model.User;

@Repository
public interface BoatProfileRepository extends JpaRepository<HomeProfile, Long> {
}
