package com.ISA.repository;

import com.ISA.domain.dto.SearchFreeHomesDTO;
import com.ISA.domain.model.HomeFreeTerms;
import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeFreeTermsRepository extends JpaRepository<HomeFreeTerms, Long> {

     List<HomeFreeTerms> findAll();
     List<HomeFreeTerms> findAllByHomeProfileId(Long homeProfileId);
}
