package com.checkyou.shinhansec.service;



import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UriRequestService {

    public final UriBuilderService uriBuilderService;

    public SearchResponse getInformation(SearchRequestDTO request) {

        URI uri = uriBuilderService.buildUri(request);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity<>(headers);

        return new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, SearchResponse.class).getBody();

    }
}
