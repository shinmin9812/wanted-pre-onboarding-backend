package com.example.wanted.controller;

import com.example.wanted.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/applies")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("")
    public void createApply(@RequestParam(value = "memberId") Long memberId, @RequestParam(value = "recruitId") Long recruitId) {
        applyService.applyToRecruit(memberId, recruitId);
    }
}
