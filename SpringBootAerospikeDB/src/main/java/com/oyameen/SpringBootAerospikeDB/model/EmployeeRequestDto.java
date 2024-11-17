package com.oyameen.SpringBootAerospikeDB.model;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class EmployeeRequestDto {

    private String id;
    private String name;
    private String jobTitle;
    private String vehicleId;
    private List<String> laptopIds;
    private List<String> mobileIds;
    private List<String> projectIds;
}
