package com.example.employeerecordapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeeRecordApplication {

    public static void main(String[] args) {

        SpringApplication.run(EmployeeRecordApplication.class, args);
    }

}
