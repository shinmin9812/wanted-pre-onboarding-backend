package com.example.wanted.controller;

import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.dto.response.RecruitResponseDto;
import com.example.wanted.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recruits")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;

    @PostMapping("/create")
    public void create(@RequestBody RecruitRequestDto recruitRequestDto) {
        recruitService.create(recruitRequestDto);
    }


    @GetMapping("")
    public List<Recruit> findAll() {
        return recruitService.findAll();
    }

    @GetMapping("/{recruit_id}")
    public RecruitResponseDto findById(@PathVariable Long recruit_id) {
        return recruitService.findById(recruit_id);
    }

}
