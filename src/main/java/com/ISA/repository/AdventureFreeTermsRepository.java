package com.ISA.repository;

import com.ISA.domain.model.AdventureFreeTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureFreeTermsRepository extends JpaRepository<AdventureFreeTerms, Long> {
    List<AdventureFreeTerms> findAll();
    List<AdventureFreeTerms> findAllByAdventureProfileId(Long adventureProfileId);
    List<AdventureFreeTerms> findAllByIsAction(boolean isAction);


}
