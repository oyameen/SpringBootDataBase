package com.oyameen.SpringBootMongoDB.controller;

import com.oyameen.SpringBootMongoDB.model.Employee;
import com.oyameen.SpringBootMongoDB.model.EmployeeRequestDto;
import com.oyameen.SpringBootMongoDB.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(201).body(employeeService.saveEmployee(employeeRequestDto));
    }

    @PutMapping(value = "/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        Employee employeeResult = employeeService.updateEmployee(employeeRequestDto);
        if (employeeResult != null)
            return ResponseEntity.ok(employeeResult);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
