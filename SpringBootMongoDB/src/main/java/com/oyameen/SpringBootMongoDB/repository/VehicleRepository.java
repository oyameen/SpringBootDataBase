package com.oyameen.SpringBootMongoDB.repository;

import com.oyameen.SpringBootMongoDB.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
