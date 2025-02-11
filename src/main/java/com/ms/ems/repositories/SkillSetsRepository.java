package com.ms.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.ems.entities.SkillSets;

@Repository
public interface SkillSetsRepository extends JpaRepository<SkillSets, Long> {
}