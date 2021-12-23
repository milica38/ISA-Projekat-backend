package com.ISA.repository;

import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ISA.domain.model.User;

import java.util.List;

@Repository
public interface HomeProfileRepository extends JpaRepository<HomeProfile, Long> {

    public List<HomeProfile> getAllByOwnerId(Long ownerId);
    public List<HomeProfile> findAll();
    public List<HomeProfile>findAllByOwnerIdAndDeleted(Long ownerId, boolean deleted);

}
