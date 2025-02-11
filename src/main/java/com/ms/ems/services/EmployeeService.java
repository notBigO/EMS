package com.ms.ems.services;

import com.ms.ems.entities.Employee;
import com.ms.ems.entities.Projects;
import com.ms.ems.entities.SkillSets;
import com.ms.ems.exceptions.CustomException;
import com.ms.ems.repositories.EmployeeRepository;
import com.ms.ems.repositories.ProjectsRepository;
import com.ms.ems.repositories.SkillSetsRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillSetsRepository skillSetsRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees from database");
        return employeeRepository.findAll();
    }

    @Cacheable(value = "employee", key = "#id")
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee by ID: {}", id);
        return employeeRepository.findById(id);
    }

    @CacheEvict(value = { "employees", "employee" }, allEntries = true)
    public Employee saveEmployee(Employee employee) {
        log.info("Saving employee: {}", employee);
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = { "employees", "employee" }, allEntries = true)
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }

    public void addSkillToEmployee(Long employeeId, Long skillId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found"));
        SkillSets skill = skillSetsRepository.findById(skillId)
                .orElseThrow(() -> new CustomException("Skill not found"));
        employee.getSkills().add(skill);
        employeeRepository.save(employee);
    }

    public void addProjectToEmployee(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found"));
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new CustomException("Project not found"));
        employee.getProjects().add(project);
        employeeRepository.save(employee);
    }

    public void addSkillToEmployee(Long employeeId, Long skillId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found"));
        SkillSets skill = skillSetsRepository.findById(skillId)
                .orElseThrow(() -> new CustomException("Skill not found"));
        employee.getSkills().add(skill);
        employeeRepository.save(employee);
    }

    public void addProjectToEmployee(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found"));
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new CustomException("Project not found"));
        employee.getProjects().add(project);
        employeeRepository.save(employee);
    }
}