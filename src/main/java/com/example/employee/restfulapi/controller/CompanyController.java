package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by HwyUee on 2018/5/7.
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    public CompanyRepository companyRepository;

    @GetMapping
    @ResponseBody
    List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional findById(@PathVariable long id) {
        return companyRepository.findById(id);
    }

    @GetMapping("/{id}/employees")
    @ResponseBody
    public List<Employee> findAllEmlpoyeesById(@PathVariable long id) {
        return companyRepository.findById(id).get().getEmployees();
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    @ResponseBody
    public List<Company> findByPageAndPageSize(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    @PostMapping
    public void AddCompany(Company company) {
        System.out.println(company.getCompanyName());
        companyRepository.save(company);
    }
}
