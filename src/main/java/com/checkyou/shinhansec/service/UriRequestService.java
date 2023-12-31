package com.checkyou.shinhansec.service;



import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import com.checkyou.shinhansec.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UriRequestService {

    public final UriBuilderService uriBuilderService;
    @Value("${request.domain}")
    private String uri;
    @Value("${request.port}")
    private int port;
    public ApiResponse<SearchResponse> getInformation(SearchRequestDTO request) throws Exception {
        final String url = String.valueOf(UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(uri)
                .port(port)
                .path("/api/securities/shinhan")
                .build()
        );

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        final WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(url)
                .build();
        final SearchResponse response;
        try {
            response = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(SearchResponse.class)
                    .block();
        }catch (Exception e){
            throw new Exception("존재하는 정보가 없습니다.");
        }
        return ApiResponse.success(response);
        
    }
}
