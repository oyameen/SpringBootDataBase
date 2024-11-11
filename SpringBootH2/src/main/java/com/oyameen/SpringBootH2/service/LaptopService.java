package com.oyameen.SpringBootH2.service;

import com.oyameen.SpringBootH2.model.Laptop;

import java.util.List;

public interface LaptopService {

    Laptop saveLaptop(Laptop laptop);

    Laptop updateLaptop(Laptop laptop);

    List<Laptop> getLaptops();

    Laptop getLaptop(Long id);

    void deleteLaptop(Long id);
}
