package com.oyameen.SpringBootMongoDB.repository;

import com.oyameen.SpringBootMongoDB.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
}
