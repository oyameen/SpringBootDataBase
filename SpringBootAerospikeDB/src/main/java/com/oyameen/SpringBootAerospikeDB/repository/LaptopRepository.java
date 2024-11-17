package com.oyameen.SpringBootAerospikeDB.repository;

import com.oyameen.SpringBootAerospikeDB.model.Laptop;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends AerospikeRepository<Laptop, String> {
}
