package com.example.wanted.controller;

import com.example.wanted.domain.Member;
import com.example.wanted.domain.Recruit;
import com.example.wanted.service.ApplyService;
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

import static org.mockito.Mockito.doNothing;

@WebMvcTest(ApplyController.class)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApplyService applyService;

    @Test
    void 채용공고_지원() throws Exception {
        //given
        Long memberId = 1L;
        Long recruitId = 3L;

        Member member = Member.builder()
                .id(1L)
                .build();

        Recruit recruit = Recruit.builder()
                .id(3L)
                .build();

        doNothing().when(applyService).applyToRecruit(memberId, recruitId);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/applies")
                        .param("memberId", member.getId().toString())
                        .param("recruitId", recruit.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        Mockito.verify(applyService, Mockito.times(1)).applyToRecruit(memberId, recruitId);

    }
}
