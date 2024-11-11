package com.oyameen.SpringBootMongoDB.service;

import com.oyameen.SpringBootMongoDB.model.Employee;
import com.oyameen.SpringBootMongoDB.model.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequestDto employee);

    Employee updateEmployee(EmployeeRequestDto employee);

    List<Employee> getEmployees();

    Employee getEmployee(String id);

    void deleteEmployee(String id);
}
