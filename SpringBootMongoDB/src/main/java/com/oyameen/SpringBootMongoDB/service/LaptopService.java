package com.oyameen.SpringBootMongoDB.service;

import com.oyameen.SpringBootMongoDB.model.Laptop;

import java.util.List;

public interface LaptopService {

    Laptop saveLaptop(Laptop laptop);

    Laptop updateLaptop(Laptop laptop);

    List<Laptop> getLaptops();

    Laptop getLaptop(String id);

    void deleteLaptop(String id);
}
