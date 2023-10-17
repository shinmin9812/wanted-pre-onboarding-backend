package com.example.wanted.service;

import com.example.wanted.domain.Company;
import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.repository.RecruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
public class RecruitServiceTest {
    RecruitService recruitService;
    RecruitRepository recruitRepository;

    @BeforeEach
    void clear() {
        recruitRepository.deleteAll();
    }

    @Test
    void createRecruit() {
        //given

        //when

        //then

    }
}
