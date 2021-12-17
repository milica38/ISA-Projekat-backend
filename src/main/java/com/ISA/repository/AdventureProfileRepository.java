package com.ISA.repository;

import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureProfileRepository extends JpaRepository<AdventureProfile, Long> {
}
