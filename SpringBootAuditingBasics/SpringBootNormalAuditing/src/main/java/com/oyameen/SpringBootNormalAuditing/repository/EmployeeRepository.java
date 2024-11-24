package com.oyameen.SpringBootNormalAuditing.repository;

import com.oyameen.SpringBootNormalAuditing.model.Employee;
import com.oyameen.SpringBootNormalAuditing.model.EmployeePkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeePkId> {
}
