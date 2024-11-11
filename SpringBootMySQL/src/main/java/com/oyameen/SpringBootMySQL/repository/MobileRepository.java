package com.oyameen.SpringBootMySQL.repository;

import com.oyameen.SpringBootMySQL.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
}
