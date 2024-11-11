package com.oyameen.SpringBootMongoDB.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

    @DocumentReference
    private Vehicle vehicle;

    @DBRef
    private List<Laptop> laptops = new ArrayList<>();

    @DBRef
    private List<Mobile> mobiles = new ArrayList<>();

    @DBRef
    private List<Project> projects = new ArrayList<>();

}
