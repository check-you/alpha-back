package com.kids.alpha.shinhansec.service;

import com.kids.alpha.shinhansec.DTO.SearchRequestDTO;
import com.kids.alpha.shinhansec.DTO.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UriBuilderService {
    private static final String SHINHAN =  "https://gapi.shinhaninvest.com:8443/openapi/v1.0/recommend/portfolio";
    private static final String KB =  "https://gapi.shinhaninvest.com:8443/openapi/v1.0/ranking/rising";
    private static final String NH =  "https://gapi.shinhaninvest.com:8443/openapi/v1.0/ranking/issue";

    public URI buildUri(SearchRequestDTO request) {

        String URL = SHINHAN;

       if(request.getBank().equals("KB")){
            URL = KB;
        }
        else if(request.getBank().equals("NH")){
            URL = NH;
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL);
        URI uri =uriBuilder.build().encode().toUri();

        return uri;

    }
}
