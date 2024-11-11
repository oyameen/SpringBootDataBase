package com.oyameen.SpringBootMongoDB.service.impl;

import com.oyameen.SpringBootMongoDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootMongoDB.model.Employee;
import com.oyameen.SpringBootMongoDB.model.Laptop;
import com.oyameen.SpringBootMongoDB.repository.EmployeeRepository;
import com.oyameen.SpringBootMongoDB.repository.LaptopRepository;
import com.oyameen.SpringBootMongoDB.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop updateLaptop(Laptop laptop) {
        Laptop laptopResult = laptopRepository.findById(laptop.getId()).orElse(null);
        if (laptopResult != null) {
            laptopResult.setBrand(laptop.getBrand());
            laptopResult.setRamSize(laptop.getRamSize());
            laptopResult.setOsType(laptop.getOsType());
            return laptopRepository.save(laptopResult);
        }
        throw new EmployeeManagementException("Cannot update this Laptop, because it doesn't exist.");
    }

    @Override
    public List<Laptop> getLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop getLaptop(String id) {
        return laptopRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLaptop(String id) {
        Laptop laptop = null;
        if ((laptop = laptopRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Laptop, because it doesn't exist.");
        }
        if (laptop.getEmployee() != null && laptop.getEmployee().getId() != null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(laptop.getEmployee().getId());
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.getLaptops().remove(laptop);
                employeeRepository.save(employee);
            }
        }
        laptopRepository.deleteById(id);
    }
}
