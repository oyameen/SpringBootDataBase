package com.oyameen.SpringBootNormalAuditing.service;

import com.oyameen.SpringBootNormalAuditing.dto.InputDto;
import com.oyameen.SpringBootNormalAuditing.model.Employee;
import com.oyameen.SpringBootNormalAuditing.model.EmployeePkId;
import com.oyameen.SpringBootNormalAuditing.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(InputDto<Employee> inputDto)
    {
        Employee employee = inputDto.getEntityModel();
        employee.setCreatedBy(inputDto.getAuthenticatedUser());
        employee.setLastModifiedBy(null);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(EmployeePkId employeePkId, InputDto<Employee> inputDto){
        Employee employeeFromDb = employeeRepository.findById(employeePkId).orElse(null);
        if (employeeFromDb == null)
        {
            throw new RuntimeException("Requested employee not fot exist.");
        }
        Employee employeeFromRequest = inputDto.getEntityModel();
        employeeFromDb.setName(employeeFromRequest.getName());
        employeeFromDb.setDateOfJoin(employeeFromRequest.getDateOfJoin());
        employeeFromDb.setSalary(employeeFromRequest.getSalary());

        employeeFromDb.setLastModifiedBy(inputDto.getAuthenticatedUser());
        return employeeRepository.save(employeeFromDb);
    }
}
