package com.example.wanted.dto.request;

import com.example.wanted.domain.Company;
import com.example.wanted.domain.Recruit;
import lombok.Data;

@Data
public class RecruitRequestDto {
    private String position;
    private int pay;
    private String content;
    private String skill;
    private Company company;

    public static Recruit toRecruit(RecruitRequestDto recruitRequestDto) {
        return Recruit.builder()
                .position(recruitRequestDto.getPosition())
                .pay(recruitRequestDto.getPay())
                .content(recruitRequestDto.getContent())
                .skill(recruitRequestDto.getSkill())
                .company(recruitRequestDto.getCompany())
                .build();
    }

    public static Recruit updateRecruitFromDto(Recruit recruit, RecruitRequestDto recruitRequestDto) {
        recruit.updateRecruit(recruitRequestDto);
        return recruit;
    }
}
