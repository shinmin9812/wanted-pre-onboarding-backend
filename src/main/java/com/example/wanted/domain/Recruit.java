package com.example.wanted.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
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
}
