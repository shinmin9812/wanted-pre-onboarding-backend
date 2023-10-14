package com.example.wanted.dto.response;

import com.example.wanted.domain.Company;
import com.example.wanted.domain.Recruit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecruitResponseDto {
    private Long id;
    private String position;
    private int pay;
    private String content;
    private String skill;
    private Company company;

    public static Recruit toEntity(RecruitResponseDto recruitResponseDto) {
        return Recruit.builder()
                .id(recruitResponseDto.getId())
                .position(recruitResponseDto.getPosition())
                .pay(recruitResponseDto.getPay())
                .content(recruitResponseDto.getContent())
                .skill(recruitResponseDto.getSkill())
                .company(recruitResponseDto.getCompany())
                .build();
    }
}
