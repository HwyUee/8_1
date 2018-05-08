package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select * from Employee e where e.gender=?1",nativeQuery = true)
    List<Employee> findByGender(String gender);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee e set e.name=?1,e.age=?2,e.gender=?3,e.companyId=?4,e.salary=?5 where e.id=?6")
    int UpdateEmployee(String name,int age,String gender,Long companyId,int salary,Long id);
}
