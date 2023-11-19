package com.checkyou.shinhansec.service;

import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UriRequestService uriRequestrService;

    public SearchResponse searchInformation(SearchRequestDTO request) {

        SearchResponse response = uriRequestrService.getInformation(request);

        return response;
    }
}
