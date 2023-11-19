package com.checkyou.shinhansec.repository;

import com.checkyou.shinhansec.domain.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {
    @Query("SELECT ea FROM EmailAuth ea " +
            "WHERE ea.email = :email " +
            "AND ea.authToken = :authToken " +
            "AND ea.expireDate >= :currentTime " +
            "AND ea.expired = false")
    Optional<EmailAuth> findValidAuthByEmail(
            @Param("email") String email,
            @Param("authToken") String authToken,
            @Param("currentTime") LocalDateTime currentTime
    );
}
