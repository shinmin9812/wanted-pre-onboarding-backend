package com.example.wanted.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Recruit {
    @Id
    @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private int pay;
    private String content;
    private String skill;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public Recruit(Long id, String position, int pay, String content, String skill, Company company) {
        this.id = id;
        this.position = position;
        this.pay = pay;
        this.content = content;
        this.skill = skill;
        this.company = company;
    }
}
