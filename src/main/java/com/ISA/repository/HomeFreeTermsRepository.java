package com.ISA.repository;

import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeFreeTermsRepository extends JpaRepository<HomeFreeTerms, Long> {

    public List<HomeFreeTerms> findAll();
    public List<HomeFreeTerms> findAllByHomeProfileId(Long homeProfileId);
}
