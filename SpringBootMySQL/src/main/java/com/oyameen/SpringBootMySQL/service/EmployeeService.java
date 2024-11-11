package com.oyameen.SpringBootMySQL.service;

import com.oyameen.SpringBootMySQL.model.Employee;
import com.oyameen.SpringBootMySQL.model.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequestDto employee);

    Employee updateEmployee(EmployeeRequestDto employee);

    List<Employee> getEmployees();

    Employee getEmployee(Long id);

    void deleteEmployee(Long id);
}
