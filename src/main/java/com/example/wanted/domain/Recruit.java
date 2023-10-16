package com.example.wanted.domain;

import com.example.wanted.dto.request.RecruitRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "recruit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit {
    @Id
    @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private int pay;
    private String content;
    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruit")
    private Set<Apply> members = new HashSet<>();

    @Builder
    public Recruit(Long id, String position, int pay, String content, String skill, Company company) {
        this.id = id;
        this.position = position;
        this.pay = pay;
        this.content = content;
        this.skill = skill;
        this.company = company;
    }

    public void updateRecruit(RecruitRequestDto recruitRequestDto) {
        if (recruitRequestDto.getPosition() != null) {
            this.position = recruitRequestDto.getPosition();
        }

        if (recruitRequestDto.getPay() != 0) {
            this.pay = recruitRequestDto.getPay();
        }
        if (recruitRequestDto.getContent() != null) {
            this.content = recruitRequestDto.getContent();
        }
        if (recruitRequestDto.getSkill() != null) {
            this.skill = recruitRequestDto.getSkill();
        }
        if (recruitRequestDto.getCompany() != null) {
            this.company = recruitRequestDto.getCompany();
        }
    }
}
