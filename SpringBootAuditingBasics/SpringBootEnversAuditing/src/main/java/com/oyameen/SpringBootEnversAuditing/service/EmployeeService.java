package com.oyameen.SpringBootEnversAuditing.service;

import com.oyameen.SpringBootEnversAuditing.model.Employee;
import com.oyameen.SpringBootEnversAuditing.model.EmployeePkId;
import com.oyameen.SpringBootEnversAuditing.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(EmployeePkId employeePkId, Employee employeeFromRequest){
        Employee employeeFromDb = employeeRepository.findById(employeePkId).orElse(null);
        if (employeeFromDb == null)
        {
            throw new RuntimeException("Requested employee not fot exist.");
        }
        employeeFromDb.setName(employeeFromRequest.getName());
        employeeFromDb.setDateOfJoin(employeeFromRequest.getDateOfJoin());
        employeeFromDb.setSalary(employeeFromRequest.getSalary());
        return employeeRepository.save(employeeFromDb);
    }

    public Revision<Integer, Employee> getInfo(EmployeePkId employeePkId)
    {
        return employeeRepository.findLastChangeRevision(employeePkId).orElse(null);
    }

    public String deleteEmployee(EmployeePkId employeePkId)
    {
        employeeRepository.deleteById(employeePkId);
        return "Employee deleted successfully.";
    }
}
