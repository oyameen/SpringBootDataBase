package com.oyameen.SpringBootAerospikeDB.model;


import lombok.*;
import org.springframework.data.aerospike.mapping.Document;


@Document
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TwoWheelVehicle extends Vehicle {
    private int size;
}
