package com.oyameen.SpringBootH2.controller;

import com.oyameen.SpringBootH2.model.Employee;
import com.oyameen.SpringBootH2.model.EmployeeRequestDto;
import com.oyameen.SpringBootH2.service.EmployeeService;
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
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
