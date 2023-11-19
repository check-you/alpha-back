package com.checkyou.shinhansec.controller;


import com.checkyou.shinhansec.DTO.SearchRequestDTO;
import com.checkyou.shinhansec.DTO.SearchResponse;
import com.checkyou.shinhansec.common.ApiResponse;
import com.checkyou.shinhansec.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<?> createAccount(@RequestBody SearchRequestDTO request){

        SearchResponse data = searchService.searchInformation(request);

        ApiResponse<SearchResponse> response = ApiResponse.success(data);

        return ResponseEntity.ok(response);
    }

}
