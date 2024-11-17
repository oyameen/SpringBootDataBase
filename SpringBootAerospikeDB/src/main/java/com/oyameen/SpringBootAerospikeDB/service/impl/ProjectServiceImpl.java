package com.oyameen.SpringBootAerospikeDB.service.impl;

import com.oyameen.SpringBootAerospikeDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootAerospikeDB.model.Project;
import com.oyameen.SpringBootAerospikeDB.repository.EmployeeRepository;
import com.oyameen.SpringBootAerospikeDB.repository.ProjectRepository;
import com.oyameen.SpringBootAerospikeDB.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Project saveProject(Project project) {
        project.setId(UUID.randomUUID().toString());
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        Project projectResult = projectRepository.findById(project.getId()).orElse(null);
        if (projectResult != null) {
            projectResult.setName(project.getName());
            projectResult.setStartDate(project.getStartDate());
            projectResult.setEndDate(project.getEndDate());
            if (projectResult.getEmployeeIds() != null && !projectResult.getEmployeeIds().isEmpty()) {
                projectResult.getEmployeeIds().forEach(employeeId -> {
                    employeeRepository.findById(employeeId).ifPresent(employee -> {
                        employee.getProjects().stream().filter(proj -> Objects.equals(proj.getId(), projectResult.getId()))
                                .findFirst().ifPresent(toRemove -> employee.getProjects().remove(toRemove));
                        employee.getProjects().add(projectResult);
                        employeeRepository.save(employee);
                    });
                });
            }
            return projectRepository.save(projectResult);
        }
        throw new EmployeeManagementException("Cannot update this Project, because it doesn't exist.");
    }

    @Override
    public List<Project> getProjects() {
        List<Project> projectList = new ArrayList<>();
        projectRepository.findAll().forEach(projectList::add);
        return projectList;
    }

    @Override
    public Project getProject(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProject(String id) {
        Project project = null;
        if ((project = projectRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Project, because it doesn't exist.");
        }
        if (project.getEmployeeIds() != null && !project.getEmployeeIds().isEmpty()) {
            Project finalProject = project;
            project.getEmployeeIds().forEach(employeeId -> {
                employeeRepository.findById(employeeId).ifPresent(employee -> {
                    if (employee.getProjects() != null && !employee.getProjects().isEmpty()) {
                        employee.getProjects().remove(finalProject);
                        employeeRepository.save(employee);
                    }
                });
            });
        }
        projectRepository.deleteById(id);
    }
}
