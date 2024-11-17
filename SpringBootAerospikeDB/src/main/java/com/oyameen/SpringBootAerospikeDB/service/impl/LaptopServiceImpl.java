package com.oyameen.SpringBootAerospikeDB.service.impl;

import com.oyameen.SpringBootAerospikeDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootAerospikeDB.model.Laptop;
import com.oyameen.SpringBootAerospikeDB.repository.EmployeeRepository;
import com.oyameen.SpringBootAerospikeDB.repository.LaptopRepository;
import com.oyameen.SpringBootAerospikeDB.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        laptop.setId(UUID.randomUUID().toString());
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop updateLaptop(Laptop laptop) {
        Laptop laptopResult = laptopRepository.findById(laptop.getId()).orElse(null);
        if (laptopResult != null) {
            laptopResult.setBrand(laptop.getBrand());
            laptopResult.setRamSize(laptop.getRamSize());
            laptopResult.setOsType(laptop.getOsType());
            if (laptopResult.getEmployeeId() != null) {
                employeeRepository.findById(laptopResult.getEmployeeId()).ifPresent(employee -> {
                    employee.getLaptops()
                            .stream().filter(lap -> Objects.equals(lap.getId(), laptopResult.getId()))
                            .findFirst().ifPresent(toRemove -> employee.getLaptops().remove(toRemove));
                    employee.getLaptops().add(laptopResult);
                    employeeRepository.save(employee);
                });
            }
            return laptopRepository.save(laptopResult);
        }
        throw new EmployeeManagementException("Cannot update this Laptop, because it doesn't exist.");
    }

    @Override
    public List<Laptop> getLaptops() {
        List<Laptop> laptopList = new ArrayList<>();
        laptopRepository.findAll().forEach(laptopList::add);
        return laptopList;
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
        if (laptop.getEmployeeId() != null) {
            Laptop finalLaptop = laptop;
            employeeRepository.findById(laptop.getEmployeeId()).ifPresent(
                    employee -> {
                        employee.getLaptops().remove(finalLaptop);
                        employeeRepository.save(employee);
                    });
        }
        laptopRepository.deleteById(id);
    }
}
