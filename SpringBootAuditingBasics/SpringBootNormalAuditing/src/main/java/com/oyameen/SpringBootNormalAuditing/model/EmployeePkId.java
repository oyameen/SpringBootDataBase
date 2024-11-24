package com.oyameen.SpringBootNormalAuditing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class EmployeePkId {

    private Integer employeeId;
    private String email;
}
