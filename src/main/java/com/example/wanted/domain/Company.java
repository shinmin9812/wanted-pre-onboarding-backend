package com.example.wanted.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String location;

    @Builder
    public Company(Long id, String name, String country, String location) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.location = location;
    }
}
