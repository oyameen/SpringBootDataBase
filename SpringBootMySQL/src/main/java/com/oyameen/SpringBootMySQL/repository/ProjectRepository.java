package com.oyameen.SpringBootMySQL.repository;

import com.oyameen.SpringBootMySQL.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
