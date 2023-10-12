package com.example.wanted.controller;

import com.example.wanted.dto.RecruitRequestDto;
import com.example.wanted.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;

    @PostMapping("/create")
    public Long create(@RequestBody RecruitRequestDto recruitRequestDto) {
        return recruitService.createRecruit(recruitRequestDto).getId();
    }

    @GetMapping("/{id}")
    public Optional<RecruitRequestDto> findById(@PathVariable Long id) {
        return recruitService.findRecruitById(id);
    }
}
