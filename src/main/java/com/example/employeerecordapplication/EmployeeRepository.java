package com.example.employeerecordapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmployeeId(String employeeId);
    Optional<EmployeeEntity> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
