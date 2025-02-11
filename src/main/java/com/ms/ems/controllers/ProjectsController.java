package com.ms.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.ems.entities.Projects;
import com.ms.ems.services.ProjectsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    @GetMapping
    public List<Projects> getAllProjects() {
        return projectsService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable Long id) {
        Optional<Projects> project = projectsService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Projects createProject(@RequestBody Projects project) {
        return projectsService.saveProject(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectsService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}