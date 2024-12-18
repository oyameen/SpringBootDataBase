package com.oyameen.SpringBootMySQL.repository;

import com.oyameen.SpringBootMySQL.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
