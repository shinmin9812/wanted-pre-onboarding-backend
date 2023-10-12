package com.example.wanted.repository;

import com.example.wanted.dto.RecruitRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitRepository extends JpaRepository<RecruitRequestDto, Long> {
    List<RecruitRequestDto> findByName(String name);
    List<RecruitRequestDto> findBySkill(String skill);

}
