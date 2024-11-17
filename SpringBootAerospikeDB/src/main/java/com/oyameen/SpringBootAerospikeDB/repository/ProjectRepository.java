package com.oyameen.SpringBootAerospikeDB.repository;

import com.oyameen.SpringBootAerospikeDB.model.Project;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends AerospikeRepository<Project, String> {
}
