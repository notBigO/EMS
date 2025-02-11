package com.ms.ems.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ms.ems.entities.SkillSets;
import com.ms.ems.repositories.SkillSetsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SkillSetsService {

    @Autowired
    private SkillSetsRepository skillSetsRepository;

    @Cacheable(value = "skills")
    public List<SkillSets> getAllSkills() {
        log.info("Fetching all skills from database");
        return skillSetsRepository.findAll();
    }

    @Cacheable(value = "skill", key = "#id")
    public Optional<SkillSets> getSkillById(Long id) {
        log.info("Fetching skill by ID: {}", id);
        return skillSetsRepository.findById(id);
    }

    @CacheEvict(value = { "skills", "skill" }, allEntries = true)
    public SkillSets saveSkill(SkillSets skill) {
        log.info("Saving skill: {}", skill);
        return skillSetsRepository.save(skill);
    }

    @CacheEvict(value = { "skills", "skill" }, allEntries = true)
    public void deleteSkill(Long id) {
        log.info("Deleting skill with ID: {}", id);
        skillSetsRepository.deleteById(id);
    }
}