package com.oyameen.SpringBootMongoDB.service;


import com.oyameen.SpringBootMongoDB.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);

    Project updateProject(Project project);

    List<Project> getProjects();

    Project getProject(String id);

    void deleteProject(String id);
}
