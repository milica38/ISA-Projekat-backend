package com.ISA.repository;

import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ISA.domain.model.User;

import java.util.List;

@Repository
public interface BoatProfileRepository extends JpaRepository<BoatProfile, Long> {
    List<BoatProfile> findAll();
    List<BoatProfile> findAllByDeleted(boolean deleted);
}
