package com.example.employee.restfulapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private Integer employeesNumber;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "companyId")
    public List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> allEmployees) {
        this.employees = allEmployees;
    }

    public Company() {
    }

    public Company(String companyName, Integer employeesNumber) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }
}
