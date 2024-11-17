package com.oyameen.SpringBootAerospikeDB.repository;

import com.oyameen.SpringBootAerospikeDB.model.FourWheelVehicle;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FourWheelVehicleRepository extends AerospikeRepository<FourWheelVehicle, String> {
}
