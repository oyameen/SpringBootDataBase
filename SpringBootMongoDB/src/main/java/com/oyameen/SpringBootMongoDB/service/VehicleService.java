package com.oyameen.SpringBootMongoDB.service;

import com.oyameen.SpringBootMongoDB.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getVehicles();

    Vehicle getVehicle(String id);

    void deleteVehicle(String id);
}
