package com.example.wanted.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "apply")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {
    @Id
    @Column(name = "apply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    @Builder
    public Apply(Long id, Member member, Recruit recruit) {
        this.id = id;
        this.member = member;
        this.recruit = recruit;
    }
}
