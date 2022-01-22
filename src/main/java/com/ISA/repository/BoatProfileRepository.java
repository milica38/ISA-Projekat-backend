package com.ISA.repository;

import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ISA.domain.model.User;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface BoatProfileRepository extends JpaRepository<BoatProfile, Long> {
     List<BoatProfile> findAll();
     List<BoatProfile> findAllByOwnerIdAndDeleted(Long ownerId, boolean deleted);
     List<BoatProfile> getAllByOwnerId(Long ownerId);
     List<BoatProfile> findAllByDeleted(boolean deleted);
     @Lock(LockModeType.PESSIMISTIC_WRITE)
     @Query("select b from BoatProfile b where b.id = :id")
     @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="100")})
     BoatProfile getLockId(@Param("id")Long id);
}
