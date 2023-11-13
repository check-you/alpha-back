package com.kids.alpha.shinhansec.service;

import com.kids.alpha.shinhansec.domain.entity.User;
import com.kids.alpha.shinhansec.jwt.JwtTokenProvider;
import com.kids.alpha.shinhansec.jwt.TokenInfo;
import com.kids.alpha.shinhansec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public ResponseEntity<TokenInfo> login(String accountNumber, String password) throws Exception {
//        try {
            HttpHeaders headers = new HttpHeaders();

            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        if (passwordEncoder.matches(password, userRepository.findByAccountNumber(accountNumber).orElseThrow().getPassword())) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountNumber, password);

                // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
                // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

                // 3. 인증 정보를 기반으로 JWT 토큰 생성
                TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

                headers.set("grantType", "Bearer");
                headers.set("accessToken", tokenInfo.getAccessToken());
                headers.set("refreshToken", tokenInfo.getRefreshToken());
                return ResponseEntity.ok().headers(headers).build();
            }
       // }
//        catch(Exception e){
//            throw new Exception("로그인 요청 실패하였습니다.");
//        }
        return ResponseEntity.badRequest().build();
    }
    @Transactional
    public ResponseEntity<String> signin(String accountNumber, String password) throws Exception {
        try {
                userRepository.save(User.builder()
                                .accountNumber(accountNumber)
                                .password(passwordEncoder.encode(password))
                        .build());
                return ResponseEntity.ok("성공적으로 회원가입이 되었습니다.");
        }catch (Exception e){
            throw new Exception("회원가입 요청에 실패하였습니다.");
        }
    }
}
