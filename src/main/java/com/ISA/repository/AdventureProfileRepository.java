package com.ISA.repository;

import com.ISA.domain.model.AdventureProfile;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;
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
public interface AdventureProfileRepository extends JpaRepository<AdventureProfile, Long> {
     List<AdventureProfile> findAll();
     List<AdventureProfile> findAllByDeleted(boolean deleted);
     @Lock(LockModeType.PESSIMISTIC_WRITE)
     @Query("select a from AdventureProfile a where a.id = :id")
     @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="100")})
     AdventureProfile getLockId(@Param("id")Long id);
}
