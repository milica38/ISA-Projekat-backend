package com.ISA.repository;

import com.ISA.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findOneByEmail(String email);
    User getByEmail(String email);
    Optional<User> findUserByRegistrationToken(String token);
    List<User> findAllUsersByStatus(String status);
}
