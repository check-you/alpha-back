package com.checkyou.shinhansec.service;

import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import com.checkyou.shinhansec.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UriRequestService uriRequestrService;

    public ApiResponse<SearchResponse> searchInformation(SearchRequestDTO request) throws Exception {
        return uriRequestrService.getInformation(request);
    }
}
