package com.kids.alpha.shinhansec.repository;

import com.kids.alpha.shinhansec.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAccountNumber(String accountNumber);

}
