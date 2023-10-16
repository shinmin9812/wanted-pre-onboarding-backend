package com.example.wanted.repository;

import com.example.wanted.domain.Recruit;
import com.example.wanted.dto.response.RecruitCompanyResponseDto;
import com.example.wanted.dto.response.RecruitResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    @Query("SELECT NEW com.example.wanted.dto.response.RecruitResponseDto(r.id, r.position, r.pay, r.skill, r.content, r.company.name, r.company.country, r.company.location) FROM recruit r JOIN r.company WHERE r.id = :id")
    Optional<RecruitResponseDto> findByIdWithCompany(@Param("id") Long id);

    @Query("SELECT NEW com.example.wanted.dto.response.RecruitCompanyResponseDto(r.id, r.position, r.pay, r.skill, r.company.name, r.company.country, r.company.location) FROM recruit r JOIN r.company")
    List<RecruitCompanyResponseDto> findAllWithCompany();

    List<RecruitCompanyResponseDto> findByCompanyNameContainingIgnoreCaseOrSkillContainingIgnoreCase(String companyName, String skill);
}
