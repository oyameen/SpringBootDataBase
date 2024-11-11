package com.oyameen.SpringBootMongoDB.model;


import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@TypeAlias("bike")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TwoWheelVehicle extends Vehicle {
    private int size;
}
