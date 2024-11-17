package com.oyameen.SpringBootAerospikeDB.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

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
    private String employeeId;

}
