package com.oyameen.SpringBootAerospikeDB.service.impl;

import com.oyameen.SpringBootAerospikeDB.exception.EmployeeManagementException;
import com.oyameen.SpringBootAerospikeDB.model.FourWheelVehicle;
import com.oyameen.SpringBootAerospikeDB.model.TwoWheelVehicle;
import com.oyameen.SpringBootAerospikeDB.model.Vehicle;
import com.oyameen.SpringBootAerospikeDB.repository.EmployeeRepository;
import com.oyameen.SpringBootAerospikeDB.repository.FourWheelVehicleRepository;
import com.oyameen.SpringBootAerospikeDB.repository.TwoWheelVehicleRepository;
import com.oyameen.SpringBootAerospikeDB.repository.VehicleRepository;
import com.oyameen.SpringBootAerospikeDB.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TwoWheelVehicleRepository twoWheelVehicleRepository;
    @Autowired
    private FourWheelVehicleRepository fourWheelVehicleRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        vehicle.setId(UUID.randomUUID().toString());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle vehicleResult = getVehicle(vehicle.getId());
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

            if (vehicleResult.getEmployeeId() != null) {
                employeeRepository.findById(vehicleResult.getEmployeeId()).ifPresent(employee -> {
                    employee.setVehicle(vehicleResult);
                    employeeRepository.save(employee);
                });
            }
            return vehicleRepository.save(vehicleResult);
        }
        throw new EmployeeManagementException("Cannot update this Vehicle, because it doesn't exist.");
    }

    @Override
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        twoWheelVehicleRepository.findAll().forEach(vehicleList::add);
        fourWheelVehicleRepository.findAll().forEach(vehicleList::add);
        return vehicleList;
    }

    @Override
    public Vehicle getVehicle(String id) {
        Vehicle vehicle = null;
        if ((vehicle = twoWheelVehicleRepository.findById(id).orElse(null)) == null) {
            vehicle = fourWheelVehicleRepository.findById(id).orElse(null);
        }
        return vehicle;
    }

    @Override
    public void deleteVehicle(String id) {
        Vehicle vehicle = null;
        if ((vehicle = getVehicle(id)) == null) {
            throw new EmployeeManagementException("Cannot delete this Vehicle, because it doesn't exist.");
        }
        if (vehicle.getEmployeeId() != null) {
            employeeRepository.findById(vehicle.getEmployeeId()).ifPresent(employee -> {
                employee.setVehicle(null);
                employeeRepository.save(employee);
            });
        }
        vehicleRepository.delete(vehicle);
    }
}
