package com.example.wanted.controller;

import com.example.wanted.domain.Company;
import com.example.wanted.dto.request.RecruitRequestDto;
import com.example.wanted.dto.response.RecruitCompanyResponseDto;
import com.example.wanted.dto.response.RecruitResponseDto;
import com.example.wanted.service.RecruitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RecruitController.class)
public class RecruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecruitService recruitService;

    @Test
    void 채용공고_등록() throws Exception {
        //given
        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .location("판교")
                .build();

        RecruitRequestDto testDto = new RecruitRequestDto();
        testDto.setPosition("백엔드 개발자");
        testDto.setPay(350000);
        testDto.setContent("네이버에서 백엔드 개발자를 모십니다.");
        testDto.setSkill("Django");
        testDto.setCompany(company);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/recruits/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        Mockito.verify(recruitService, Mockito.times(1)).createRecruit(Mockito.any(RecruitRequestDto.class));
    }

    @Test
    void 채용공고_수정() throws Exception {
        //given
        Long recruitId = 1L;
        Company company = Company.builder()
                .name("네이버")
                .country("한국")
                .location("분당")
                .build();

        RecruitRequestDto testDto = new RecruitRequestDto();
        testDto.setPosition("프론트엔드 개발자");
        testDto.setPay(350000);
        testDto.setContent("네이버에서 프론트엔드 개발자를 모십니다.");
        testDto.setSkill("React");
        testDto.setCompany(company);

        //when
        mockMvc.perform(MockMvcRequestBuilders.patch("/recruits/{recruit_id}", recruitId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        Mockito.verify(recruitService, Mockito.times(1))
                .updateRecruit(Mockito.eq(recruitId), Mockito.any(RecruitRequestDto.class));

    }

    @Test
    void 채용공고_삭제() throws Exception {
        //given
        Long recruitId = 1L;

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/recruits/{recruit_id}", recruitId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        Mockito.verify(recruitService, Mockito.times(1))
                .deleteRecruit(Mockito.eq(recruitId));
    }

    @Test
    void 채용공고_목록_조회() throws Exception {
        //given
        RecruitCompanyResponseDto testDto1 = RecruitCompanyResponseDto.builder()
                .id(1L)
                .position("백엔드 개발자")
                .pay(350000)
                .skill("Spring")
                .companyName("네이버")
                .companyCountry("한국")
                .companyLocation("판교")
                .build();

        RecruitCompanyResponseDto testDto2 = RecruitCompanyResponseDto.builder()
                .id(2L)
                .position("백엔드 개발자")
                .pay(400000)
                .skill("Spring")
                .companyName("배달의민족")
                .companyCountry("한국")
                .companyLocation("판교")
                .build();

        List<RecruitCompanyResponseDto> recruitList = Arrays.asList(testDto1, testDto2);

        Mockito.when(recruitService.findAll()).thenReturn(recruitList);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recruits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].position").value("백엔드 개발자"))
                .andExpect(jsonPath("$[0].pay").value(350000))
                .andExpect(jsonPath("$[0].skill").value("Spring"))
                .andExpect(jsonPath("$[0].companyName").value("네이버"))
                .andExpect(jsonPath("$[0].companyCountry").value("한국"))
                .andExpect(jsonPath("$[0].companyLocation").value("판교"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].position").value("백엔드 개발자"))
                .andExpect(jsonPath("$[1].pay").value(400000))
                .andExpect(jsonPath("$[1].skill").value("Spring"))
                .andExpect(jsonPath("$[1].companyName").value("배달의민족"))
                .andExpect(jsonPath("$[1].companyCountry").value("한국"))
                .andExpect(jsonPath("$[1].companyLocation").value("판교"));

        //then
        Mockito.verify(recruitService, Mockito.times(1)).findAll();

    }

    @Test
    void 채용공고_상세_조회() throws Exception {
        //given
        Long recruitId = 1L;

        RecruitResponseDto testDto = new RecruitResponseDto(1L, "백엔드 개발자", 350000, "Spring", "test1234", "네이버", "한국", "판교");

        Mockito.when(recruitService.findById(recruitId)).thenReturn(testDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recruits/{recruit_id}", recruitId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        Mockito.verify(recruitService, Mockito.times(1)).findById(1L);

    }

    @Test
    void 채용공고_검색() throws Exception {
        //given
        String keyword = "Spring";

        RecruitCompanyResponseDto testDto1 = RecruitCompanyResponseDto.builder()
                .id(1L)
                .position("백엔드 개발자")
                .pay(350000)
                .skill("Spring")
                .companyName("네이버")
                .companyCountry("한국")
                .companyLocation("판교")
                .build();

        RecruitCompanyResponseDto testDto2 = RecruitCompanyResponseDto.builder()
                .id(2L)
                .position("백엔드 개발자")
                .pay(400000)
                .skill("Spring")
                .companyName("배달의민족")
                .companyCountry("한국")
                .companyLocation("판교")
                .build();

        List<RecruitCompanyResponseDto> lists = Arrays.asList(testDto1, testDto2);

        Mockito.when(recruitService.searchRecruits(keyword)).thenReturn(lists);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recruits/search")
                        .param("keyword", keyword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].position").value("백엔드 개발자"))
                .andExpect(jsonPath("$[0].pay").value(350000))
                .andExpect(jsonPath("$[0].skill").value("Spring"))
                .andExpect(jsonPath("$[0].companyName").value("네이버"))
                .andExpect(jsonPath("$[0].companyCountry").value("한국"))
                .andExpect(jsonPath("$[0].companyLocation").value("판교"));

        //then
        Mockito.verify(recruitService, Mockito.times(1)).searchRecruits(keyword);

    }
}
