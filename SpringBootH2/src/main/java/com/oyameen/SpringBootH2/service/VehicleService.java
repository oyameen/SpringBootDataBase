package com.oyameen.SpringBootH2.service;

import com.oyameen.SpringBootH2.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getVehicles();

    Vehicle getVehicle(Long id);

    void deleteVehicle(Long id);
}
