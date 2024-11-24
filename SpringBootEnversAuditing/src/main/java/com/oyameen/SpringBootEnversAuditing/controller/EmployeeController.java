package com.oyameen.SpringBootEnversAuditing.controller;

import com.oyameen.SpringBootEnversAuditing.model.Employee;
import com.oyameen.SpringBootEnversAuditing.model.EmployeePkId;
import com.oyameen.SpringBootEnversAuditing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        return ResponseEntity.status(201).body(employeeService.addEmployee(employee));
    }


    @PutMapping("/updateEmployee/{id}/{email}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @PathVariable String email, @RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(new EmployeePkId(id,email),employee));
    }

    @GetMapping("/getInfo/{id}/{email}")
    public ResponseEntity<Revision<Integer, Employee>> getInfo(@PathVariable Integer id, @PathVariable String email)
    {
        Revision<Integer, Employee> revision = employeeService.getInfo(new EmployeePkId(id, email));
        if (revision == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(revision);
    }

    @DeleteMapping("/deleteEmployee/{id}/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id, @PathVariable String email)
    {
        return ResponseEntity.ok(employeeService.deleteEmployee(new EmployeePkId(id, email)));
    }
}
