package com.kids.alpha.shinhansec.repository;

import com.kids.alpha.shinhansec.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountNumber(String accountNumber);

}
