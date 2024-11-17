package com.oyameen.SpringBootAerospikeDB.service;

import com.oyameen.SpringBootAerospikeDB.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getVehicles();

    Vehicle getVehicle(String id);

    void deleteVehicle(String id);
}
