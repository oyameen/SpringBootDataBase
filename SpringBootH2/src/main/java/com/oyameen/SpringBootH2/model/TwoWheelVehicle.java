package com.oyameen.SpringBootH2.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@DiscriminatorValue("Bike")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TwoWheelVehicle extends Vehicle {
    private int size;
}
