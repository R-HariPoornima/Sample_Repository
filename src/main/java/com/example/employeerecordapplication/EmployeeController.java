package com.example.employeerecordapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest request) {
      return employeeService.addEmployee(request);
    }


    @PutMapping
    public EmployeeResponse updateEmployee(@RequestBody EmployeeRequest request) {
        return employeeService.updateEmployee(request);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        employeeService.deleteByName(name);
        return ResponseEntity.ok("Employee deleted: " + name);
    }

    @GetMapping
    public Map<String, EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
