package com.oyameen.SpringBootMongoDB.repository;

import com.oyameen.SpringBootMongoDB.model.Mobile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends MongoRepository<Mobile, String> {
}
