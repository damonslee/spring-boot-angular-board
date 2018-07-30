package com.tram.springbootangularboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findBySocialId(String socialId);
    Optional<Account> findByEmail(String email);
}
