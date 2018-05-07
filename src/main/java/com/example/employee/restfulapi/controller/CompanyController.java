package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public String AddCompany(Company company) {
        if (companyRepository.saveAndFlush(company) != null) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String UpdateCompany(@ModelAttribute Company company, @PathVariable long id) {
        if (companyRepository.findById(id).get() == null) {
            throw new Error("The id :" + id + "not exist in table");
        }
        if (company.getCompanyName() != null) {
            System.out.println("company.getEmployeesNumber()    " + company.getEmployeesNumber());
            if (company.getEmployeesNumber() != null) {
                //将公司名和员工数量同时更新
                if (companyRepository.UpdateAllById(company.getCompanyName(), company.getEmployeesNumber(), id) == 1) {
                    return "更新company成功！";
                }
                return "更新company失败";
            } else {
                if (companyRepository.UpdateCompanyNameById(company.getCompanyName(), id) == 1) {
                    return "更新company成功！";
                }
                return "更新company失败";
            }
        } else {
            if (companyRepository.UpdateCompanyNumberById(company.getEmployeesNumber(), id) == 1) {
                return "更新company成功！";
            }
            return "更新company失败";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String DeleteCompany(@PathVariable long id) {
        companyRepository.deleteById(id);
        return "删除company成功";
    }
}


