package com.oyameen.SpringBootAerospikeDB.repository;

import com.oyameen.SpringBootAerospikeDB.model.Employee;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends AerospikeRepository<Employee, String> {
}
