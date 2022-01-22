package com.ISA.repository;

import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface HomeProfileRepository extends JpaRepository<HomeProfile, Long> {

     List<HomeProfile> getAllByOwnerId(Long ownerId);
     List<HomeProfile> findAll();
     List<HomeProfile>findAllByOwnerIdAndDeleted(Long ownerId, boolean deleted);
     List<HomeProfile> findAllByDeleted(boolean deleted);
     @Lock(LockModeType.PESSIMISTIC_WRITE)
     @Query("select h from HomeProfile h where h.id = :id")
     @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="100")})
     HomeProfile getLockId(@Param("id")Long id);
}
