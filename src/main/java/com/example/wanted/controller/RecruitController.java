package com.example.wanted.controller;

import com.example.wanted.dto.RecruitRequestDto;
import com.example.wanted.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;

    @PostMapping("/create")
    public void create(@RequestBody RecruitRequestDto recruitRequestDto) {
        recruitService.create(recruitRequestDto);
    }

}
