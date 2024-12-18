package com.oyameen.SpringBootMongoDB.repository;

import com.oyameen.SpringBootMongoDB.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
