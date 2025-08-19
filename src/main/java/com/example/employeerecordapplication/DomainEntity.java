//package com.example.employeerecordapplication;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class DomainEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true)
//    private String domainName;
//
//    @Builder.Default
//    @JsonBackReference
//    @OneToMany(
//            mappedBy = "domain",
//            orphanRemoval = true,
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private Set<EmployeeEntity> employees=new HashSet<>();
//
//
//
//
//
//
//
//}
package com.example.employeerecordapplication;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String domainName;

    @ManyToMany(mappedBy = "domains", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @Builder.Default
    private Set<EmployeeEntity> employees = new HashSet<>();
}
