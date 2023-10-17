package com.example.wanted.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitCompanyResponseDto {
    private Long id;
    private String position;
    private int pay;
    private String skill;
    private String companyName;
    private String companyCountry;
    private String companyLocation;

    public RecruitCompanyResponseDto(Long id, String position, int pay, String skill, String companyName, String companyCountry, String companyLocation) {
        this.id = id;
        this.position = position;
        this.pay = pay;
        this.skill = skill;
        this.companyName = companyName;
        this.companyCountry = companyCountry;
        this.companyLocation = companyLocation;
    }
}
