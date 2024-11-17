package com.oyameen.SpringBootAerospikeDB.service;


import com.oyameen.SpringBootAerospikeDB.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);

    Project updateProject(Project project);

    List<Project> getProjects();

    Project getProject(String id);

    void deleteProject(String id);
}
