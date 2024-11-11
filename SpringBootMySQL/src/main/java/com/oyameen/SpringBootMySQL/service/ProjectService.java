package com.oyameen.SpringBootMySQL.service;


import com.oyameen.SpringBootMySQL.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);

    Project updateProject(Project project);

    List<Project> getProjects();

    Project getProject(Long id);

    void deleteProject(Long id);
}
