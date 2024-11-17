package com.oyameen.SpringBootAerospikeDB.model;

import lombok.*;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Employee {
    @Id
    private String id;
    private String name;
    private String jobTitle;
    private Vehicle vehicle;
    private List<Laptop> laptops = new ArrayList<>();
    private List<Mobile> mobiles = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

}
