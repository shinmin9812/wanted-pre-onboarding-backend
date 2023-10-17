package com.example.wanted.service;

import com.example.wanted.domain.Apply;
import com.example.wanted.domain.Member;
import com.example.wanted.domain.Recruit;
import com.example.wanted.exception.ExistsApplyException;
import com.example.wanted.repository.ApplyRepository;
import com.example.wanted.repository.MemberRepository;
import com.example.wanted.repository.RecruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
public class ApplyServiceTest {
    @Mock
    private ApplyRepository applyRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RecruitRepository recruitRepository;

    @InjectMocks
    private ApplyService applyService;

    @BeforeEach
    public void clean() {
        applyRepository.deleteAll();
    }

    @Test
    void 채용공고_지원() throws Exception {
        //given
        Long memberId = 1L;
        Long recruitId = 2L;

        Member member = Member.builder()
                .id(memberId)
                .build();

        Recruit recruit = Recruit.builder()
                .id(recruitId)
                .build();

        //when
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(recruitRepository.findById(recruitId)).thenReturn(Optional.of(recruit));
        when(applyRepository.existsByMemberIdAndRecruitId(memberId, recruitId)).thenReturn(false);

        //then
        assertDoesNotThrow(() -> applyService.applyToRecruit(memberId, recruitId));
    }

    @Test
    void 이미_지원한_채용공고_지원() throws Exception {
        //given
        Long memberId = 1L;
        Long recruitId = 2L;

        Member member = Member.builder()
                .id(memberId)
                .build();

        Recruit recruit = Recruit.builder()
                .id(recruitId)
                .build();

        //when
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(recruitRepository.findById(recruitId)).thenReturn(Optional.of(recruit));
        when(applyRepository.existsByMemberIdAndRecruitId(memberId, recruitId)).thenReturn(true);

        //then
        assertThrows(ExistsApplyException.class, () -> applyService.applyToRecruit(memberId, recruitId));
    }
}
