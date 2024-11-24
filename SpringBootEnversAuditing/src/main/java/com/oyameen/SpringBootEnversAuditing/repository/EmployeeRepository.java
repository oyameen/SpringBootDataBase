package com.oyameen.SpringBootEnversAuditing.repository;

import com.oyameen.SpringBootEnversAuditing.model.Employee;
import com.oyameen.SpringBootEnversAuditing.model.EmployeePkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends RevisionRepository<Employee, EmployeePkId, Integer>, JpaRepository<Employee, EmployeePkId> {
}
