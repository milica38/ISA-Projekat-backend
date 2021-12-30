package com.ISA.repository;

import com.ISA.domain.model.BoatFreeTerms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatFreeTermsRepository  extends JpaRepository<BoatFreeTerms, Long> {

    public List<BoatFreeTerms> findAll();
    public List<BoatFreeTerms> findAllByBoatProfileId(Long boatProfileId);
}
