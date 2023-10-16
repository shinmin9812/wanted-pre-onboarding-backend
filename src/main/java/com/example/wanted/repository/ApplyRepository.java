package com.example.wanted.repository;

import com.example.wanted.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

    boolean existsByMemberIdAndRecruitId(Long memberId, Long recruitId);
}
