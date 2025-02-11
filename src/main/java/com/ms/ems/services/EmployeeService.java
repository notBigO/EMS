package com.ms.ems.services;

import com.ms.ems.entities.Employee;
import com.ms.ems.exceptions.DuplicateEmailException;
import com.ms.ems.exceptions.EmployeeNotFoundException;
import com.ms.ems.exceptions.ProjectNotFoundException;
import com.ms.ems.exceptions.SkillNotFoundException;
import com.ms.ems.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repository;

    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        log.info("Getting all employees from database");
        return repository.findAll();
    }

    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee {} from DB", id);
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @CachePut(value = "employees", key = "#result.id")
    public Employee createEmployee(Employee employee) {
        if (repository.findByEmail(employee.getEmail()).isPresent())
            throw new DuplicateEmailException("Email exists: " + employee.getEmail());
        log.info("Saving new employee: {}", employee.getEmail());
        return repository.save(employee);
    }

    @CachePut(value = "employees", key = "#id")
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = getEmployeeById(id);
        if (updated.getName() != null)
            existing.setName(updated.getName());
        if (updated.getEmail() != null && !updated.getEmail().equals(existing.getEmail())) {
            if (repository.findByEmail(updated.getEmail()).isPresent())
                throw new DuplicateEmailException("Email exists: " + updated.getEmail());
            existing.setEmail(updated.getEmail());
        }
        if (updated.getRole() != null)
            existing.setRole(updated.getRole());
        if (updated.getDepartment() != null)
            existing.setDepartment(updated.getDepartment());
        return repository.save(existing);
    }

    @CacheEvict(value = "employees", key = "#id")
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        log.info("Deleting employee: {}", id);
        repository.delete(employee);
    }

    @CachePut(value = "employees", key = "#id")
    public Employee addSkill(Long id, String skill) {
        Employee employee = getEmployeeById(id);
        employee.getSkills().add(skill);
        return repository.save(employee);
    }

    @CachePut(value = "employees", key = "#id")
    public Employee removeSkill(Long id, String skill) {
        Employee employee = getEmployeeById(id);
        if (!employee.getSkills().remove(skill))
            throw new SkillNotFoundException("Skill not found: " + skill);
        return repository.save(employee);
    }

    @CachePut(value = "employees", key = "#id")
    public Employee assignProject(Long id, String project) {
        Employee employee = getEmployeeById(id);
        employee.getAssignedProjects().add(project);
        return repository.save(employee);
    }

    @CachePut(value = "employees", key = "#id")
    public Employee unassignProject(Long id, String project) {
        Employee employee = getEmployeeById(id);
        if (!employee.getAssignedProjects().remove(project))
            throw new ProjectNotFoundException("Project not found: " + project);
        return repository.save(employee);
    }
}
