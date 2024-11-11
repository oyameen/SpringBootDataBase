package com.oyameen.SpringBootMySQL.repository;

import com.oyameen.SpringBootMySQL.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
