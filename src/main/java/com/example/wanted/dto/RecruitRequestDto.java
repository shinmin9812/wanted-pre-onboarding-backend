package com.example.wanted.dto;

import com.example.wanted.domain.Recruit;
import lombok.Data;

@Data
public class RecruitRequestDto {
    private String position;
    private int pay;
    private String content;
    private String skill;

    public static Recruit toRecruit(RecruitRequestDto recruitRequestDto) {
        return Recruit.builder()
                .position(recruitRequestDto.getPosition())
                .pay(recruitRequestDto.getPay())
                .content(recruitRequestDto.getContent())
                .skill(recruitRequestDto.getSkill())
                .build();
    }
}
