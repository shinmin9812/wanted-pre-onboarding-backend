package com.example.wanted.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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
