package com.kids.alpha.shinhansec.controller;

import com.kids.alpha.shinhansec.DTO.AccountDeleteRequestDTO;
import com.kids.alpha.shinhansec.DTO.AccountRequestDTO;
import com.kids.alpha.shinhansec.DTO.SearchRequestDTO;
import com.kids.alpha.shinhansec.DTO.SearchResponse;
import com.kids.alpha.shinhansec.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final SearchService searchService;


    @GetMapping("/search")
    public ResponseEntity<?> createAccount(@RequestBody SearchRequestDTO request){

        SearchResponse response = searchService.searchInformation(request);

        return ResponseEntity.ok(response);
    }

}
