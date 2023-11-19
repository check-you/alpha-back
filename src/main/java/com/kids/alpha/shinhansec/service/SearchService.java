package com.kids.alpha.shinhansec.service;

import com.kids.alpha.shinhansec.DTO.AccountDeleteRequestDTO;
import com.kids.alpha.shinhansec.DTO.AccountRequestDTO;
import com.kids.alpha.shinhansec.DTO.SearchRequestDTO;
import com.kids.alpha.shinhansec.DTO.SearchResponse;
import com.kids.alpha.shinhansec.domain.entity.Account;
import com.kids.alpha.shinhansec.domain.entity.Member;
import com.kids.alpha.shinhansec.jwt.JwtTokenProvider;
import com.kids.alpha.shinhansec.jwt.TokenInfo;
import com.kids.alpha.shinhansec.repository.AccountRepository;
import com.kids.alpha.shinhansec.repository.MemberRepository;
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
public class SearchService {

    private final UriRequestService uriRequestrService;

    public SearchResponse searchInformation(SearchRequestDTO request) {

        SearchResponse response = uriRequestrService.getInformation(request);

        return response;
    }
}
