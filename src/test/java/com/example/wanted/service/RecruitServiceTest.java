package com.example.wanted.service;

import com.example.wanted.domain.Company;
import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.dto.response.RecruitCompanyResponseDto;
import com.example.wanted.dto.response.RecruitResponseDto;
import com.example.wanted.exception.NotFoundException;
import com.example.wanted.repository.RecruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
public class RecruitServiceTest {
    @Mock
    private RecruitRepository recruitRepository;

    @InjectMocks
    private RecruitService recruitService;

    @BeforeEach
    public void clean() {
        recruitRepository.deleteAll();
    }

    private static final Logger logger = LoggerFactory.getLogger(RecruitServiceTest.class);

    @Test
    void 채용공고_등록() throws Exception {
        //given
        Company company = Company.builder()
                .id(1L)
                .name("naver")
                .country("Korea")
                .location("Seoul")
                .build();

        RecruitRequestDto recruitRequestDto = new RecruitRequestDto();
        recruitRequestDto.setPosition("Backend");
        recruitRequestDto.setPay(10000);
        recruitRequestDto.setContent("Backend Developer");
        recruitRequestDto.setSkill("Spring");
        recruitRequestDto.setCompany(company);

        Recruit recruit = RecruitRequestDto.toRecruit(recruitRequestDto);

        when(recruitRepository.save(Mockito.any(Recruit.class))).thenReturn(recruit);
        //when
        Recruit createRecruit = recruitService.createRecruit(recruitRequestDto);

        //then
        assertThat(recruit).isEqualTo(createRecruit);
    }

    @Test
    void 채용공고_수정() throws Exception {
        //given
        Long recruitId = 1L;

        Company company = Company.builder()
                .id(1L)
                .name("naver")
                .country("Korea")
                .location("Seoul")
                .build();

        RecruitRequestDto updateRecruitDto = new RecruitRequestDto();
        updateRecruitDto.setPosition("Frontend");
        updateRecruitDto.setPay(200000);
        updateRecruitDto.setContent("Frontend Developer");
        updateRecruitDto.setSkill("React");
        updateRecruitDto.setCompany(company);

        Recruit originalRecruit = Recruit.builder()
                .id(recruitId)
                .position("Backend")
                .pay(250000)
                .content("Backend Developer")
                .skill("Spring")
                .company(company)
                .build();

        when(recruitRepository.findById(recruitId)).thenReturn(Optional.of(originalRecruit));
        when(recruitRepository.save(Mockito.any(Recruit.class))).thenReturn(originalRecruit);
        //when
        Recruit updateRecruit = recruitService.updateRecruit(recruitId, updateRecruitDto);

        //then
        assertThat(updateRecruit).isEqualTo(originalRecruit);
    }

    @Test
    void 채용공고_삭제() throws Exception {
        //given
        Long recruitId = 1L;

        Company company = Company.builder()
                .id(1L)
                .name("wanted")
                .country("Korea")
                .location("Seoul")
                .build();

        Recruit recruit = Recruit.builder()
                .id(recruitId)
                .position("Backend")
                .pay(350000)
                .content("Backend Developer")
                .skill("Spring")
                .company(company)
                .build();

        recruitRepository.save(recruit);

        //when
        //then
        assertThrows(NullPointerException.class, () -> recruitService.deleteRecruit(recruitId));
    }

    @Test
    void 채용공고_목록_조회() throws Exception {
        //given
        RecruitCompanyResponseDto recruit1 = RecruitCompanyResponseDto.builder()
                .id(1L)
                .position("Backend")
                .pay(150000)
                .skill("Spring")
                .companyName("naver")
                .companyCountry("Korea")
                .companyLocation("Pangyo")
                .build();

        RecruitCompanyResponseDto recruit2 = RecruitCompanyResponseDto.builder()
                .id(2L)
                .position("Backend")
                .pay(150000)
                .skill("Spring")
                .companyName("wanted")
                .companyCountry("Korea")
                .companyLocation("Seoul")
                .build();

        List<RecruitCompanyResponseDto> recruits = Arrays.asList(recruit1, recruit2);

        when(recruitService.findAll()).thenReturn(recruits);
        //when
        List<RecruitCompanyResponseDto> result = recruitService.findAll();

        //then
        assertThat(recruits).isEqualTo(result);
    }


    @Test
    void 채용공고_상세_조회() throws Exception {
        //given
        Long recruitId = 1L;

        RecruitResponseDto recruitResponseDto = RecruitResponseDto.builder()
                .id(recruitId)
                .position("Backend")
                .pay(250000)
                .skill("Spring")
                .companyName("wanted")
                .companyCountry("Korea")
                .companyLocation("Seoul")
                .build();

        when(recruitRepository.findByIdWithCompany(recruitId)).thenReturn(Optional.of(recruitResponseDto));
        //when
        RecruitResponseDto recruit1 = recruitService.findById(recruitId);

        //then
        assertThat(recruit1).isEqualTo(recruitResponseDto);
    }

    @Test
    void 채용공고_검색() throws Exception {
        //given
        String keyword = "Spring";

        RecruitCompanyResponseDto recruit1 = RecruitCompanyResponseDto.builder()
                .id(1L)
                .position("Backend")
                .pay(150000)
                .skill("Spring")
                .companyName("naver")
                .companyCountry("Korea")
                .companyLocation("Pangyo")
                .build();

        RecruitCompanyResponseDto recruit2 = RecruitCompanyResponseDto.builder()
                .id(2L)
                .position("Backend")
                .pay(150000)
                .skill("Spring")
                .companyName("wanted")
                .companyCountry("Korea")
                .companyLocation("Seoul")
                .build();

        List<RecruitCompanyResponseDto> recruits = Arrays.asList(recruit1, recruit2);

        when(recruitService.searchRecruits(keyword)).thenReturn(recruits);
        //when
        List<RecruitCompanyResponseDto> result = recruitService.searchRecruits(keyword);

        //then
        assertThat(recruits).isEqualTo(result);

    }
}
