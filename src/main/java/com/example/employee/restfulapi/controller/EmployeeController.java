package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    public EmployeeRepository employeeRepository;

    /**
     * 查询所有的Employees
     * @return
     */
    @GetMapping
    @ResponseBody
    List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * 根据职工id查询Employee
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Optional findEmployeeById(@PathVariable long id) {
        return employeeRepository.findById(id);
    }

    /**
     * 分页查询Employees
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{page}/pageSize/{pageSize}")
    @ResponseBody
    public List<Employee> findByPageAndPageSize(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize) {
        return employeeRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    /**
     * 返回所有的性别为male的所有职工
     * @return
     */
    @GetMapping("/male")
    @ResponseBody
    public List<Employee>  getMaleEmployees(){
        return employeeRepository.findByGender("male");
    }

    /**
     * 添加职工
     * @param employee
     * @return
     */
    @PostMapping
    public String addEmployee(Employee employee){
        if (employeeRepository.saveAndFlush(employee) != null) {
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 更新职工信息
     * @param id
     * @param e
     * @return
     */
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id,Employee e){
        Optional e1 = employeeRepository.findById(id);
        if (e1 == null){
            return "该职工不存在";
        }


        return  null;
    }

    /**
     * 根据职工id删除职工
     * @param id
     * @return
     * @throws Exception
     */
    @ExceptionHandler
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) throws Exception{
        long oldCount = employeeRepository.count();
        employeeRepository.deleteById(id);
        if (employeeRepository.count() == oldCount-1) {
            return "删除职工成功";
        }
        return "删除职工失败";
    }

}
