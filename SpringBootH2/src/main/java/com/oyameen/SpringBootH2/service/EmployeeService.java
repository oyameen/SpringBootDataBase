package com.oyameen.SpringBootH2.service;

import com.oyameen.SpringBootH2.model.Employee;
import com.oyameen.SpringBootH2.model.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequestDto employee);

    Employee updateEmployee(EmployeeRequestDto employee);

    List<Employee> getEmployees();

    Employee getEmployee(Long id);

    void deleteEmployee(Long id);
}
