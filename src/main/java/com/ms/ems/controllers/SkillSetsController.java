package com.ms.ems.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.ems.entities.SkillSets;
import com.ms.ems.services.SkillSetsService;

@RestController
@RequestMapping("/api/skills")
public class SkillSetsController {

    @Autowired
    private SkillSetsService skillSetsService;

    @GetMapping
    public List<SkillSets> getAllSkills() {
        return skillSetsService.getAllSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillSets> getSkillById(@PathVariable Long id) {
        Optional<SkillSets> skill = skillSetsService.getSkillById(id);
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SkillSets createSkill(@RequestBody SkillSets skill) {
        return skillSetsService.saveSkill(skill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillSetsService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}