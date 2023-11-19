package com.checkyou.shinhansec.repository;

import com.checkyou.shinhansec.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAccountNumber(String accountNumber);
    Optional<Member> findByEmail(String email);
}
