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
public class Mobile {

    @Id
    private String id;
    private String brand;
    private int cameraNumber;
    private int ramSize;
    private MobileOSType osType;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @DBRef
    private Employee employee;

}