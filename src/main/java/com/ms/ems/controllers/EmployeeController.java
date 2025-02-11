package com.ms.ems.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.ems.dtos.ProjectRequest;
import com.ms.ems.dtos.SkillRequest;
import com.ms.ems.entities.Employee;
import com.ms.ems.services.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(service.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(service.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<Employee> addSkill(@PathVariable Long id, @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(service.addSkill(id, request.getSkill()));
    }

    @DeleteMapping("/{id}/skills/{skill}")
    public ResponseEntity<Employee> removeSkill(@PathVariable Long id, @PathVariable String skill) {
        return ResponseEntity.ok(service.removeSkill(id, skill));
    }

    @PostMapping("/{id}/projects")
    public ResponseEntity<Employee> assignProject(@PathVariable Long id, @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(service.assignProject(id, request.getProject()));
    }

    @DeleteMapping("/{id}/projects/{project}")
    public ResponseEntity<Employee> unassignProject(@PathVariable Long id, @PathVariable String project) {
        return ResponseEntity.ok(service.unassignProject(id, project));
    }
}