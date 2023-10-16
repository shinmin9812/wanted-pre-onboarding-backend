package com.example.wanted.service;

import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.dto.response.RecruitCompanyResponseDto;
import com.example.wanted.dto.response.RecruitResponseDto;
import com.example.wanted.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;

    //채용공고 생성
    public Recruit createRecruit(RecruitRequestDto recruitRequestDto) {
        Recruit recruit = RecruitRequestDto.toRecruit(recruitRequestDto);

        return recruitRepository.save(recruit);
    }

    //채용 공고 수정
    public Recruit updateRecruit(Long id, RecruitRequestDto recruitRequestDto) {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(RuntimeException::new);
        Recruit updateRecruit = RecruitRequestDto.updateRecruitFromDto(recruit, recruitRequestDto);

        return recruitRepository.save(updateRecruit);
    }

    //채용 공고 삭제
    public void deleteRecruit(Long id) {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(RuntimeException::new);

        recruitRepository.deleteById(id);
    }

    //채용 공고 목록 조회
    public List<RecruitCompanyResponseDto> findAll() {
        return recruitRepository.findAllWithCompany();
    }

    //채용 공고 상세 조회
    public RecruitResponseDto findById(Long id) {
        RecruitResponseDto RecruitResponseDto = recruitRepository.findByIdWithCompany(id).orElseThrow(RuntimeException::new);

        return RecruitResponseDto;
    }

}
