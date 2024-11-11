package com.oyameen.SpringBootMongoDB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Project {

    @Id
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @DBRef
    private List<Employee> employees = new ArrayList<>();
}
