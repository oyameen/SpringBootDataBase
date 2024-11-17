package com.oyameen.SpringBootAerospikeDB.service;

import com.oyameen.SpringBootAerospikeDB.model.Employee;
import com.oyameen.SpringBootAerospikeDB.model.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequestDto employee);

    Employee updateEmployee(EmployeeRequestDto employee);

    List<Employee> getEmployees();

    Employee getEmployee(String id);

    void deleteEmployee(String id);
}
