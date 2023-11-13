package com.kids.alpha.shinhansec.jwt;

import com.kids.alpha.shinhansec.domain.entity.UserInfo;
import com.kids.alpha.shinhansec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        return userRepository.findByAccountNumber(accountNumber)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(com.kids.alpha.shinhansec.domain.entity.User user) {
        UserInfo userInfo = new UserInfo(
                user.getId(),
                user.getUsername()
        );
        return User.builder()
                .username(userInfo.toString())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
