package com.oyameen.SpringBootMongoDB.repository;

import com.oyameen.SpringBootMongoDB.model.Laptop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends MongoRepository<Laptop, String> {
}
