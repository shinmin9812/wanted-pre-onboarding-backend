package com.example.wanted.service;

import com.example.wanted.dto.RecruitRequestDto;
import com.example.wanted.repository.RecruitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RecruitService {
    private final RecruitRepository recruitRepository;

    public RecruitService(RecruitRepository recruitRepository) {
        this.recruitRepository = recruitRepository;
    }

    //채용 공고 등록
    public RecruitRequestDto createRecruit(RecruitRequestDto recruitDto) {
        return recruitRepository.save(recruitDto);
    }

    //채용 공고 수정
//    public Optional<Recruit> updateRecruit(Recruit recruit) {
//
//    }

    //채용 공고 삭제
    public void deleteRecruit(Long recruitId) {
        recruitRepository.deleteById(recruitId);
    }

    //채용 공고 목록 조회
    public List<RecruitRequestDto> findAllRecruits() {
        return recruitRepository.findAll();
    }

    //채용 공고 상세 조회
    public Optional<RecruitRequestDto> findRecruitById(Long recruitId) {
        return recruitRepository.findById(recruitId);
    }

    //채용 공고 검색
}
