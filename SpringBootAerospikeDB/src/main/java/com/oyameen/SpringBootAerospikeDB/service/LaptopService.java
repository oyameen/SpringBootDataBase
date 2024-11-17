package com.oyameen.SpringBootAerospikeDB.service;

import com.oyameen.SpringBootAerospikeDB.model.Laptop;

import java.util.List;

public interface LaptopService {

    Laptop saveLaptop(Laptop laptop);

    Laptop updateLaptop(Laptop laptop);

    List<Laptop> getLaptops();

    Laptop getLaptop(String id);

    void deleteLaptop(String id);
}
