package com.oyameen.SpringBootAerospikeDB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

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
    private List<String> employeeIds = new ArrayList<>();
}
