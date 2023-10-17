package com.example.wanted.service;

import com.example.wanted.domain.Apply;
import com.example.wanted.domain.Member;
import com.example.wanted.domain.Recruit;
import com.example.wanted.exception.ExistsApplyException;
import com.example.wanted.exception.NotFoundException;
import com.example.wanted.repository.ApplyRepository;
import com.example.wanted.repository.MemberRepository;
import com.example.wanted.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;

    public void applyToRecruit(Long memberId, Long recruitId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));
        Recruit recruit = recruitRepository.findById(recruitId).orElseThrow(() -> new NotFoundException("존재하지 않는 채용 공고입니다."));

        //해당 채용공고에 지원했는지 확인
        boolean IsApplied = applyRepository.existsByMemberIdAndRecruitId(memberId, recruitId);

        if (IsApplied) {
            throw new ExistsApplyException("이미 지원한 채용 공고입니다.");
        } else {
            Apply apply = Apply.builder()
                    .member(member)
                    .recruit(recruit)
                    .build();

            applyRepository.save(apply);
        }
    }
}
