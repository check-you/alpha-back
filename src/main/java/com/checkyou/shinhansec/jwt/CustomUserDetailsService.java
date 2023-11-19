package com.checkyou.shinhansec.jwt;

import com.checkyou.shinhansec.domain.entity.UserInfo;
import com.checkyou.shinhansec.repository.MemberRepository;
import com.checkyou.shinhansec.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        UserInfo userInfo = new UserInfo(
                member.getId(),
                member.getUsername()
        );
        return User.builder()
                .username(userInfo.toString())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }
}
