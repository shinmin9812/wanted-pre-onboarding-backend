package com.example.wanted.dto;

import com.example.wanted.domain.Company;
import com.example.wanted.domain.Recruit;
import lombok.Data;

@Data
public class RecruitRequestDto {
    private Long id;
    private String position;
    private int pay;
    private String content;
    private String skill;
    private Company company;

    public Recruit toRecruit() {
        return Recruit.builder()
                .position(position)
                .pay(pay)
                .content(content)
                .skill(skill)
                .company(company)
                .build();
    }
}
