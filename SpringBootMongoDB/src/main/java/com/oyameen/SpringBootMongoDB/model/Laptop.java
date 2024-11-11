package com.oyameen.SpringBootMongoDB.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Laptop {

    @Id
    private String id;
    private String brand;
    private int ramSize;
    private LaptopOSType osType;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @DBRef
    private Employee employee;

}
