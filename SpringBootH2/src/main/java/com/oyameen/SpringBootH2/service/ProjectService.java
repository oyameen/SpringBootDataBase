package com.oyameen.SpringBootH2.service;


import com.oyameen.SpringBootH2.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);

    Project updateProject(Project project);

    List<Project> getProjects();

    Project getProject(Long id);

    void deleteProject(Long id);
}
