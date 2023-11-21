package com.checkyou.shinhansec.service;

import com.checkyou.shinhansec.DTO.AccountDeleteRequestDTO;
import com.checkyou.shinhansec.DTO.AccountRequestDTO;
import com.checkyou.shinhansec.DTO.AccountResponseDTO;
import com.checkyou.shinhansec.DTO.RequestPrincipalDTO;
import com.checkyou.shinhansec.common.ApiResponse;
import com.checkyou.shinhansec.domain.entity.Account;
import com.checkyou.shinhansec.domain.entity.EmailAuth;
import com.checkyou.shinhansec.domain.entity.Member;
import com.checkyou.shinhansec.jwt.JwtTokenProvider;
import com.checkyou.shinhansec.jwt.TokenInfo;
import com.checkyou.shinhansec.repository.AccountRepository;
import com.checkyou.shinhansec.repository.EmailAuthRepository;
import com.checkyou.shinhansec.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailAuthRepository emailAuthRepository;
    private final EmailService emailService;

    @Transactional
    public ResponseEntity<TokenInfo> login(String email, String password) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
            if (passwordEncoder.matches(password, memberRepository.findByEmail(email).orElseThrow().getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

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
        } catch (Exception e) {
            throw new Exception("로그인 요청 실패하였습니다.");
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    public ResponseEntity<String> signIn(String email, String name, String phoneNumber, String password) throws Exception {
        if (checkDuplicateEmail(email)) //중복 이메일 있으면 false
            return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");

        try {
            EmailAuth emailAuth = emailAuthRepository.save(
                    EmailAuth.builder()
                            .email(email)
                            .authToken(UUID.randomUUID().toString())
                            .expired(false)
                            .build()
            );


            memberRepository.save(
                    Member.builder()
                            .email(email)
                            .phoneNumber(phoneNumber)
                            .name(name)
                            .password(passwordEncoder.encode(password))
                            .emailAuth(false)
                            .build()
            );
            emailService.send(emailAuth.getEmail(), emailAuth.getAuthToken());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new Exception("회원가입에 실패하였습니다.");
        }
    }

    private boolean checkDuplicateEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void confirmEmail(String email, String token) {
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(email, token, LocalDateTime.now())
                .orElseThrow();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        emailAuth.useToken();
        member.emailVerifiedSuccess();
    }

    @Transactional
    public ApiResponse<String> createNewAccount(AccountRequestDTO request, String principal) throws Exception {
        try {
            if (checkDuplicateAccount(request.getAccount()))
                throw new Exception("중복된 계좌 번호입니다.");
            String email = new ObjectMapper().readValue(principal, RequestPrincipalDTO.class).getNickname();

            Member member = memberRepository.findByEmail(email).orElseThrow();

            Account account = Account.builder()
                    .account(request.getAccount())
                    .bank(request.getBank())
                    .category(request.getCategory())
                    .member(member)
                    .build();

            return ApiResponse.success(accountRepository.save(account).getAccount() + " 계좌 생성 완료되었습니다.");
        } catch (Exception e) {
            throw new Exception("계좌 생성에 실패하였습니다.");
        }
    }

    private boolean checkDuplicateAccount(String account) {
        return accountRepository.findByAccount(account).isPresent();
    }

    @Transactional
    public void deleteAccount(AccountDeleteRequestDTO request) throws Exception {
        try {
            Account account = accountRepository.findByAccount(request.getAccount()).orElseThrow();

            accountRepository.delete(account);

        } catch (Exception e) {
            throw new Exception("계좌 삭제에 실패하였습니다.");
        }
    }

    public ApiResponse<Boolean> checkEmail(String email) throws Exception {
        try {
            return ApiResponse.success(memberRepository.findByEmail(email).orElseThrow().getEmailAuth());
        } catch (Exception e) {
            throw new Exception("이메일 인증이 완료되지 않았습니다.");
        }
    }

    public ApiResponse<AccountResponseDTO> getAccounts(String email) throws Exception {
        try {
            Member member = memberRepository.findByEmail(email).orElseThrow();
            List<Account> accountList = accountRepository.findByMember(member);

            List<AccountResponseDTO.AccountList> list = accountList.stream()
                    .map(account -> new AccountResponseDTO.AccountList(
                            account.getAccount(),
                            account.getBank(),
                            account.getCategory()))
                    .collect(Collectors.toList());

            return ApiResponse.success(new AccountResponseDTO(member.getName(), accountList.size(), list));
        } catch (Exception e) {
            throw new Exception("에러 발생");
        }
    }

    public ApiResponse<String> getName(String email) throws Exception {
        try {
            return ApiResponse.success(memberRepository.findByEmail(email).orElseThrow().getName());
        }catch (Exception e){
            throw new Exception("이름을 가져오는 데 실패하였습니다.");
        }
    }
}
