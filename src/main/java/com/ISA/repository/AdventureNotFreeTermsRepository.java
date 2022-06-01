package com.ISA.repository;

import com.ISA.domain.model.AdventureFreeTerms;
import com.ISA.domain.model.AdventureNotFreeTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureNotFreeTermsRepository extends JpaRepository<AdventureNotFreeTerms, Long> {
    List<AdventureNotFreeTerms> findAll();
    List<AdventureNotFreeTerms> findAllByAdventureProfileId(Long adventureProfileId);



}
