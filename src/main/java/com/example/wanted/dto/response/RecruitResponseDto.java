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
    private String skill;
    private String content;
    private String companyName;
    private String companyCountry;
    private String companyLocation;

    public RecruitResponseDto(Long id, String position, int pay, String skill, String content, String companyName, String companyCountry, String companyLocation) {
        this.id = id;
        this.position = position;
        this.pay = pay;
        this.skill = skill;
        this.content = content;
        this.companyName = companyName;
        this.companyCountry = companyCountry;
        this.companyLocation = companyLocation;
    }
}
