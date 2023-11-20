package com.checkyou.shinhansec.repository;


import com.checkyou.shinhansec.domain.entity.Account;
import com.checkyou.shinhansec.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccount(String account);
    List<Account> findByMember(Member member);
}
