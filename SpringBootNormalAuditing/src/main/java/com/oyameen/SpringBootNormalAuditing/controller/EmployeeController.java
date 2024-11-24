package com.oyameen.SpringBootNormalAuditing.controller;

import com.oyameen.SpringBootNormalAuditing.dto.InputDto;
import com.oyameen.SpringBootNormalAuditing.model.Employee;
import com.oyameen.SpringBootNormalAuditing.model.EmployeePkId;
import com.oyameen.SpringBootNormalAuditing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody InputDto<Employee> inputDto)
    {
        return ResponseEntity.status(201).body(employeeService.addEmployee(inputDto));
    }


    @PutMapping("/updateEmployee/{id}/{email}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @PathVariable String email, @RequestBody InputDto<Employee> inputDto)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(new EmployeePkId(id,email),inputDto));
    }
}
