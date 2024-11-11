package com.oyameen.SpringBootMongoDB.service.impl;

import com.oyameen.SpringBootMongoDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootMongoDB.model.Employee;
import com.oyameen.SpringBootMongoDB.model.Project;
import com.oyameen.SpringBootMongoDB.repository.EmployeeRepository;
import com.oyameen.SpringBootMongoDB.repository.ProjectRepository;
import com.oyameen.SpringBootMongoDB.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        Project projectResult = projectRepository.findById(project.getId()).orElse(null);
        if (projectResult != null) {
            projectResult.setName(project.getName());
            projectResult.setStartDate(project.getStartDate());
            projectResult.setEndDate(project.getEndDate());
            return projectRepository.save(projectResult);
        }
        throw new EmployeeManagementException("Cannot update this Project, because it doesn't exist.");
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
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
        if (project.getEmployees() != null && !project.getEmployees().isEmpty()) {
            Project finalProject = project;
            project.getEmployees().forEach(emp -> {
                if (emp != null && emp.getId() != null) {
                    Optional<Employee> employeeOptional = employeeRepository.findById(emp.getId());
                    if (employeeOptional.isPresent()) {
                        Employee employee = employeeOptional.get();
                        employee.getProjects().remove(finalProject);
                        employeeRepository.save(employee);
                    }
                }
            });
        }
        projectRepository.deleteById(id);
    }
}
