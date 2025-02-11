package com.ms.ems.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ms.ems.entities.Projects;
import com.ms.ems.repositories.ProjectsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Cacheable(value = "projects")
    public List<Projects> getAllProjects() {
        log.info("Fetching all projects from database");
        return projectsRepository.findAll();
    }

    @Cacheable(value = "project", key = "#id")
    public Optional<Projects> getProjectById(Long id) {
        log.info("Fetching project by ID: {}", id);
        return projectsRepository.findById(id);
    }

    @CacheEvict(value = { "projects", "project" }, allEntries = true)
    public Projects saveProject(Projects project) {
        log.info("Saving project: {}", project);
        return projectsRepository.save(project);
    }

    @CacheEvict(value = { "projects", "project" }, allEntries = true)
    public void deleteProject(Long id) {
        log.info("Deleting project with ID: {}", id);
        projectsRepository.deleteById(id);
    }
}