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
public class Vehicle {

    @Id
    private String id;
    private String model;
    private String licenceNumber;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private String employeeId;
}
