package com.example.wanted.service;

import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.dto.response.RecruitResponseDto;
import com.example.wanted.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //채용 공고 수정


    //채용 공고 삭제


    //채용 공고 목록 조회
    public List<Recruit> findAll() {
        return recruitRepository.findAll();
    }

    //채용 공고 상세 조회
    public RecruitResponseDto findById(Long id) {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(RuntimeException::new);

        RecruitResponseDto recruitResponseDto = RecruitResponseDto.builder()
                .id(recruit.getId())
                .position(recruit.getPosition())
                .pay(recruit.getPay())
                .content(recruit.getContent())
                .skill(recruit.getSkill())
                .company(recruit.getCompany())
                .build();

        return recruitResponseDto;
    }

}
