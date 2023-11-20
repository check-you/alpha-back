package com.checkyou.shinhansec.controller;

import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import com.checkyou.shinhansec.common.ApiResponse;
import com.checkyou.shinhansec.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {
    private final SearchService searchService;
    @PostMapping("/search")
    public ApiResponse<SearchResponse> searchAccount(@RequestBody SearchRequestDTO request) throws Exception {
        return ApiResponse.success(searchService.searchInformation(request).getData());
        }
}
