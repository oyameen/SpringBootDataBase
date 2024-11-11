package com.oyameen.SpringBootMySQL.service;

import com.oyameen.SpringBootMySQL.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getVehicles();

    Vehicle getVehicle(Long id);

    void deleteVehicle(Long id);
}
