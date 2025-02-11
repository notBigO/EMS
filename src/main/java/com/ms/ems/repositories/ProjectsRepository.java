package com.ms.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.ems.entities.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
}