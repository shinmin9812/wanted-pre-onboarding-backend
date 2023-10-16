package com.example.wanted.controller;

import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.dto.response.RecruitCompanyResponseDto;
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
    public void createRecruit(@RequestBody RecruitRequestDto recruitRequestDto) {
        recruitService.createRecruit(recruitRequestDto);
    }

    @PatchMapping("/{recruit_id}")
    public void updateRecruit(@PathVariable Long recruit_id, @RequestBody RecruitRequestDto recruitRequestDto) {
        recruitService.updateRecruit(recruit_id, recruitRequestDto);
    }

    @DeleteMapping("/{recruit_id}")
    public void deleteRecruit(@PathVariable Long recruit_id) {
        recruitService.deleteRecruit(recruit_id);
    }

    @GetMapping("")
    public List<RecruitCompanyResponseDto> findAll() {
        return recruitService.findAll();
    }

    @GetMapping("/{recruit_id}")
    public RecruitResponseDto findById(@PathVariable Long recruit_id) {
        return recruitService.findById(recruit_id);
    }

    @GetMapping("/search")
    public List<RecruitCompanyResponseDto> search(@RequestParam(value = "keyword") String keyword) {
        return recruitService.searchRecruits(keyword);
    }
}
