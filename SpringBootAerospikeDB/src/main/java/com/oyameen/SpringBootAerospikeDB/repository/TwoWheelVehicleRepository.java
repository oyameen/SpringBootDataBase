package com.oyameen.SpringBootAerospikeDB.repository;

import com.oyameen.SpringBootAerospikeDB.model.TwoWheelVehicle;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoWheelVehicleRepository extends AerospikeRepository<TwoWheelVehicle, String> {
}
