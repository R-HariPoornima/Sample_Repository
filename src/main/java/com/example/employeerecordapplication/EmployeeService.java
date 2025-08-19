//package com.example.employeerecordapplication;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeeService {
//    private final EmployeeRepository employeeRepository;
//    private final DomainRepository domainRepository;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public EmployeeResponse addEmployee(EmployeeRequest request) {
////        if (employeeRepository.findByEmployeeId(request.getEmployeeId()).isPresent()) {
////            throw new RuntimeException("Employee ID already exists");
////        }
//
//        DomainEntity domain = domainRepository.findByDomainName(request.getDomainName())
//                .orElseGet(() -> {
//                    DomainEntity newDomain = DomainEntity.builder()
//                            .domainName(request.getDomainName())
//                            .build();
//                    return domainRepository.save(newDomain);
//                });
//
//        EmployeeEntity employee = EmployeeEntity.builder()
//                .employeeId(request.getEmployeeId())
//                .name(request.getName())
//                .domain(domain)
//                .build();
//
//        domain.getEmployees().add(employee);
//
//        domainRepository.save(domain);
//
//        return EmployeeResponse.builder()
//                .name(employee.getName())
//                .employeeId(employee.getEmployeeId())
//                .domainName(domain.getDomainName())
//                .build();
//    }
//
//    public EmployeeResponse updateEmployee(EmployeeRequest request) {
//        EmployeeEntity employee = employeeRepository.findByEmployeeId(request.getEmployeeId())
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        employee.setName(request.getName());
//
//        DomainEntity domain = domainRepository.findByDomainName(request.getDomainName())
//                .orElseGet(() -> domainRepository.save(
//                        DomainEntity.builder()
//                                .domainName(request.getDomainName())
//                                .build()
//                ));
//
//        employee.setDomain(domain);
//        employeeRepository.save(employee);
//
//        return EmployeeResponse.builder()
//                .name(employee.getName())
//                .employeeId(employee.getEmployeeId())
//                .domainName(domain.getDomainName())
//                .build();
//    }
//
//    public void deleteByName(String name) {
//        employeeRepository.deleteByName(name);
//    }
//
//    public Map<String, EmployeeResponse> getAllEmployees() {
//        List<EmployeeEntity> list = employeeRepository.findAll();
//        Map<String, EmployeeResponse> map = new HashMap<>();
//        for (EmployeeEntity e : list) {
//            map.put(e.getEmployeeId(),
//                    EmployeeResponse.builder()
//                            .name(e.getName())
//                            .employeeId(e.getEmployeeId())
//                            .domainName(e.getDomain().getDomainName())
//                            .build());
//        }
//        return map;
//    }
//
//}
package com.example.employeerecordapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DomainRepository domainRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EmployeeResponse addEmployee(EmployeeRequest request) {
        if (employeeRepository.findByEmployeeId(request.getEmployeeId()).isPresent()) {
            throw new RuntimeException("Employee already exists");
        }

        Set<DomainEntity> domains = request.getDomainName().stream()
                .map(name -> domainRepository.findByDomainName(name)
                        .orElseGet(() -> domainRepository.save(
                                DomainEntity.builder().domainName(name).build())))
                .collect(Collectors.toSet());

        EmployeeEntity employee = EmployeeEntity.builder()
                .employeeId(request.getEmployeeId())
                .name(request.getName())
                .domains(domains)
                .build();

        employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .name(employee.getName())
                .employeeId(employee.getEmployeeId())
                .domainName(domains.stream().map(DomainEntity::getDomainName).toList())
                .build();
    }

    public EmployeeResponse updateEmployee(EmployeeRequest request) {
        EmployeeEntity employee = employeeRepository.findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Set<DomainEntity> updatedDomains = request.getDomainName().stream()
                .map(name -> domainRepository.findByDomainName(name)
                        .orElseGet(() -> domainRepository.save(
                                DomainEntity.builder().domainName(name).build())))
                .collect(Collectors.toSet());

        employee.setName(request.getName());
        employee.setDomains(updatedDomains);
        employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .name(employee.getName())
                .employeeId(employee.getEmployeeId())
                .domainName(updatedDomains.stream().map(DomainEntity::getDomainName).toList())
                .build();
    }

    public void deleteByName(String name)
    {
        employeeRepository.deleteByName(name);
    }

    public Map<String, EmployeeResponse> getAllEmployees() {
        List<EmployeeEntity> list = employeeRepository.findAll();
        Map<String, EmployeeResponse> map = new HashMap<>();
        for (EmployeeEntity e : list) {
            map.put(e.getEmployeeId(),
                    EmployeeResponse.builder()
                            .name(e.getName())
                            .employeeId(e.getEmployeeId())
                            .domainName(e.getDomains().stream().map(DomainEntity::getDomainName).toList())
                            .build());
        }
        return map;
    }
}
