package com.oyameen.SpringBootAerospikeDB.service.impl;

import com.oyameen.SpringBootAerospikeDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootAerospikeDB.model.*;
import com.oyameen.SpringBootAerospikeDB.repository.*;
import com.oyameen.SpringBootAerospikeDB.service.EmployeeService;
import com.oyameen.SpringBootAerospikeDB.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public Employee saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID().toString());
        employee.setName(employeeRequestDto.getName());
        employee.setJobTitle(employeeRequestDto.getJobTitle());
        List<Laptop> laptops = new ArrayList<>();
        List<Mobile> mobiles = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        Vehicle vehicle = preSaveValidation(employeeRequestDto, employee, laptops, mobiles, projects);

        employeeRepository.save(employee);
        vehicleRepository.save(vehicle);
        laptopRepository.saveAll(laptops);
        mobileRepository.saveAll(mobiles);
        projectRepository.saveAll(projects);

        employee.setVehicle(vehicle);
        employee.setLaptops(laptops);
        employee.setMobiles(mobiles);
        employee.setProjects(projects);

        return employeeRepository.save(employee);
    }

    private Vehicle preSaveValidation(EmployeeRequestDto employeeRequestDto, Employee employee,
                                      List<Laptop> laptops, List<Mobile> mobiles, List<Project> projects) {

        Vehicle vehicle = vehicleService.getVehicle(employeeRequestDto.getVehicleId());
        if (vehicle == null)
            throw new EmployeeManagementException("Cannot add the employee, because the vehicle with id = " + employeeRequestDto.getVehicleId() + ", doesn't exist.");
        else if (vehicle.getEmployeeId() != null) {
            throw new EmployeeManagementException("Cannot add the employee, because the vehicle was used by other employee.");
        }
        vehicle.setEmployeeId(employee.getId());


        if (employeeRequestDto.getLaptopIds() == null)
            throw new EmployeeManagementException("LaptopIds cannot be null.");
        employeeRequestDto.getLaptopIds().forEach(id -> {
            Laptop laptop = laptopRepository.findById(id).orElse(null);
            if (laptop == null)
                throw new EmployeeManagementException("Cannot add the employee, because the laptop with id = " + id + ", doesn't exist.");
            else if (laptop.getEmployeeId() != null)
                throw new EmployeeManagementException("Cannot add the employee, because the laptop was used by other employee.");
            laptop.setEmployeeId(employee.getId());
            laptops.add(laptop);

        });


        if (employeeRequestDto.getMobileIds() == null)
            throw new EmployeeManagementException("MobileIds cannot be null.");
        employeeRequestDto.getMobileIds().forEach(id -> {
            Mobile mobile = mobileRepository.findById(id).orElse(null);
            if (mobile == null)
                throw new EmployeeManagementException("Cannot add the employee, because the mobile with id = " + id + ", doesn't exist.");
            else if (mobile.getEmployeeId() != null)
                throw new EmployeeManagementException("Cannot add the employee, because the mobile was used by other employee.");
            mobile.setEmployeeId(employee.getId());
            mobiles.add(mobile);
        });


        if (employeeRequestDto.getProjectIds() == null)
            throw new EmployeeManagementException("ProjectIds cannot be null.");
        employeeRequestDto.getProjectIds().forEach(id -> {
            Project project = projectRepository.findById(id).orElse(null);
            if (project == null)
                throw new EmployeeManagementException("Cannot add the employee, because the project with id = " + id + ", doesn't exist.");
            project.getEmployeeIds().add(employee.getId());
            projects.add(project);
        });


        return vehicle;
    }

    @Override
    public Employee updateEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = employeeRepository.findById(employeeRequestDto.getId()).orElse(null);
        if (employee == null)
            throw new EmployeeManagementException("Cannot update the employee with id  = " + employeeRequestDto.getId() + ",  because it doesn't exist.");

        employee.setName(employeeRequestDto.getName());
        employee.setJobTitle(employeeRequestDto.getJobTitle());
        List<Laptop> laptops = new ArrayList<>();
        List<Mobile> mobiles = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        Vehicle vehicle = preUpdateValidation(employeeRequestDto, employee, laptops, mobiles, projects);

        vehicleRepository.save(vehicle);
        laptopRepository.saveAll(laptops);
        mobileRepository.saveAll(mobiles);
        projectRepository.saveAll(projects);

        employee.setVehicle(vehicle);
        employee.setLaptops(laptops);
        employee.setMobiles(mobiles);
        employee.setProjects(projects);

        return employeeRepository.save(employee);
    }

    private Vehicle preUpdateValidation(EmployeeRequestDto employeeRequestDto, Employee employee,
                                        List<Laptop> laptops, List<Mobile> mobiles, List<Project> projects) {

        Vehicle vehicle = vehicleService.getVehicle(employeeRequestDto.getVehicleId());
        if (vehicle == null)
            throw new EmployeeManagementException("Cannot update the employee, because the vehicle with id = " + employeeRequestDto.getVehicleId() + ", doesn't exist.");
        else if (vehicle.getEmployeeId() != null && !Objects.equals(vehicle.getEmployeeId(), employee.getId())) {
            throw new EmployeeManagementException("Cannot update the employee, because the vehicle was used by other employee.");
        }
        if (employee.getVehicle() != null && employee.getVehicle() != vehicle) {
            employee.getVehicle().setEmployeeId(null);
            vehicleRepository.save(employee.getVehicle());
        }
        vehicle.setEmployeeId(employee.getId());


        if (employeeRequestDto.getLaptopIds() == null)
            throw new EmployeeManagementException("LaptopIds cannot be null.");
        employeeRequestDto.getLaptopIds().forEach(id -> {
            Laptop laptop = laptopRepository.findById(id).orElse(null);
            if (laptop == null)
                throw new EmployeeManagementException("Cannot update the employee, because the laptop with id = " + id + ", doesn't exist.");
            else if (laptop.getEmployeeId() != null && !Objects.equals(laptop.getEmployeeId(), employee.getId()))
                throw new EmployeeManagementException("Cannot update the employee, because the laptop was used by other employee.");
            if (!employee.getLaptops().contains(laptop))
                laptop.setEmployeeId(employee.getId());

            laptops.add(laptop);

        });
        employee.getLaptops().forEach(laptop -> {
            if (!laptops.contains(laptop)) {
                laptop.setEmployeeId(null);
                laptopRepository.save(laptop);
            }
        });


        if (employeeRequestDto.getMobileIds() == null)
            throw new EmployeeManagementException("MobileIds cannot be null.");
        employeeRequestDto.getMobileIds().forEach(id -> {
            Mobile mobile = mobileRepository.findById(id).orElse(null);
            if (mobile == null)
                throw new EmployeeManagementException("Cannot update the employee, because the mobile with id = " + id + ", doesn't exist.");
            else if (mobile.getEmployeeId() != null && !Objects.equals(mobile.getEmployeeId(), employee.getId()))
                throw new EmployeeManagementException("Cannot update the employee, because the mobile was used by other employee.");
            if (!employee.getMobiles().contains(mobile))
                mobile.setEmployeeId(employee.getId());
            mobiles.add(mobile);
        });
        employee.getMobiles().forEach(mobile -> {
            if (!mobiles.contains(mobile)) {
                mobile.setEmployeeId(null);
                mobileRepository.save(mobile);
            }
        });


        if (employeeRequestDto.getProjectIds() == null)
            throw new EmployeeManagementException("ProjectIds cannot be null.");
        employeeRequestDto.getProjectIds().forEach(id -> {
            Project project = projectRepository.findById(id).orElse(null);
            if (project == null)
                throw new EmployeeManagementException("Cannot update the employee, because the project with id = " + id + ", doesn't exist.");
            if (!employee.getProjects().contains(project))
                project.getEmployeeIds().add(employee.getId());
            projects.add(project);
        });
        employee.getProjects().forEach(project -> {
            if (!projects.contains(project)) {
                project.getEmployeeIds().remove(employee.getId());
                projectRepository.save(project);
            }
        });


        return vehicle;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @Override
    public Employee getEmployee(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = null;
        if ((employee = employeeRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Employee, because it doesn't exist.");
        }
        if (employee.getVehicle() != null)
            throw new EmployeeManagementException("Cannot delete this Employee, because it's vehicle still exist.");
        if (employee.getLaptops() != null && !employee.getLaptops().isEmpty())
            throw new EmployeeManagementException("Cannot delete this Employee, because it's laptops still exist.");
        if (employee.getMobiles() != null && !employee.getMobiles().isEmpty())
            throw new EmployeeManagementException("Cannot delete this Employee, because it's mobiles still exist.");
        if (employee.getProjects() != null && !employee.getProjects().isEmpty())
            throw new EmployeeManagementException("Cannot delete this Employee, because it's projects still exist.");
        employeeRepository.deleteById(id);
    }

}
