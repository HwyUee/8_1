package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Company c set c.companyName=?1,c.employeesNumber=?2 where c.id=?3")
    int  UpdateAllById (String companyName,Integer employeesNumber,long id);
    @Transactional
    @Modifying
    @Query("UPDATE Company c set c.companyName=?1 where c.id=?2")
    int  UpdateCompanyNameById (String companyName,long id);
    @Transactional
    @Modifying
    @Query("UPDATE Company c set c.employeesNumber=?1 where c.id=?2")
    int  UpdateCompanyNumberById (Integer employeesNumber,long id);


}
