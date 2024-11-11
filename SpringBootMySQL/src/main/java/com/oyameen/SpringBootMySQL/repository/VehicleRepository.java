package com.oyameen.SpringBootMySQL.repository;

import com.oyameen.SpringBootMySQL.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
