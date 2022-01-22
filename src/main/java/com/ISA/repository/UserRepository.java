package com.ISA.repository;

import com.ISA.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findOneByEmail(String email);
    User getByEmail(String email);
    Optional<User> findUserByRegistrationToken(String token);
    List<User> findAllByType(String type);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User save(User request);

}
