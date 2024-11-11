package com.oyameen.SpringBootMongoDB.service.impl;

import com.oyameen.SpringBootMongoDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootMongoDB.model.Employee;
import com.oyameen.SpringBootMongoDB.model.FourWheelVehicle;
import com.oyameen.SpringBootMongoDB.model.TwoWheelVehicle;
import com.oyameen.SpringBootMongoDB.model.Vehicle;
import com.oyameen.SpringBootMongoDB.repository.EmployeeRepository;
import com.oyameen.SpringBootMongoDB.repository.VehicleRepository;
import com.oyameen.SpringBootMongoDB.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle vehicleResult = vehicleRepository.findById(vehicle.getId()).orElse(null);
        if (vehicleResult != null) {
            vehicleResult.setModel(vehicle.getModel());
            vehicleResult.setLicenceNumber(vehicle.getLicenceNumber());
            if (vehicle instanceof TwoWheelVehicle &&
                    vehicleResult instanceof TwoWheelVehicle &&
                    ((TwoWheelVehicle) vehicle).getSize() > 0)

                ((TwoWheelVehicle) vehicleResult).setSize(((TwoWheelVehicle) vehicle).getSize());

            else if (vehicle instanceof FourWheelVehicle &&
                    vehicleResult instanceof FourWheelVehicle &&
                    ((FourWheelVehicle) vehicle).getWeight() > 0)

                ((FourWheelVehicle) vehicleResult).setWeight(((FourWheelVehicle) vehicle).getWeight());

            return vehicleRepository.save(vehicleResult);
        }
        throw new EmployeeManagementException("Cannot update this Vehicle, because it doesn't exist.");
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicle(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVehicle(String id) {
        Vehicle vehicle = null;
        if ((vehicle = vehicleRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Vehicle, because it doesn't exist.");
        }
        if (vehicle.getEmployee() != null && vehicle.getEmployee().getId() != null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(vehicle.getEmployee().getId());
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setVehicle(null);
                employeeRepository.save(employee);
            }
        }
        vehicleRepository.deleteById(id);
    }
}
