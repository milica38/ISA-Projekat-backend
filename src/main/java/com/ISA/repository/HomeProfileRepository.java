package com.ISA.repository;

import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeProfileRepository extends JpaRepository<HomeProfile, Long> {

     List<HomeProfile> getAllByOwnerId(Long ownerId);
     List<HomeProfile> findAll();
     List<HomeProfile>findAllByOwnerIdAndDeleted(Long ownerId, boolean deleted);
     List<HomeProfile> findAllByDeleted(boolean deleted);

}
