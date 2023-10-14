package com.example.wanted.service;

import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.RecruitRequestDto;
import com.example.wanted.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;

    //채용공고 생성
    public void create(RecruitRequestDto recruitRequestDto) {
        Recruit recruit = RecruitRequestDto.toRecruit(recruitRequestDto);
        recruitRepository.save(recruit);
    }

}
