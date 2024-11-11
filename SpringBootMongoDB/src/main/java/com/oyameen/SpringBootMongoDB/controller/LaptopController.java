package com.oyameen.SpringBootMongoDB.controller;

import com.oyameen.SpringBootMongoDB.model.Laptop;
import com.oyameen.SpringBootMongoDB.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @PostMapping(value = "/laptop")
    public ResponseEntity<Laptop> addLaptop(@RequestBody Laptop laptop) {
        return ResponseEntity.status(201).body(laptopService.saveLaptop(laptop));
    }

    @PutMapping(value = "/laptop")
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop) {
        Laptop laptopResult = laptopService.updateLaptop(laptop);
        if (laptopResult != null)
            return ResponseEntity.ok(laptopResult);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/laptop")
    public ResponseEntity<List<Laptop>> getLaptops() {
        return ResponseEntity.ok(laptopService.getLaptops());
    }

    @GetMapping(value = "/laptop/{id}")
    public ResponseEntity<Laptop> getLaptop(@PathVariable("id") String id) {
        Laptop laptop = laptopService.getLaptop(id);
        if (laptop != null) {
            return ResponseEntity.ok(laptop);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/laptop/{id}")
    public ResponseEntity<Laptop> deleteLaptop(@PathVariable("id") String id) {
        laptopService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }
}
