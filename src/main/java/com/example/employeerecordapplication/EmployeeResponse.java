package com.example.employeerecordapplication;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeResponse {
    private String name;
    private String employeeId;
    private List<String> domainName;

}
