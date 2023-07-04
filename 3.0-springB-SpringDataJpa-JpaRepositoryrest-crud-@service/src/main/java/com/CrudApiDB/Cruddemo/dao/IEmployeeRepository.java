package com.CrudApiDB.Cruddemo.dao;

import com.CrudApiDB.Cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    //bu kadar. baska kod yazmaya gerek yok.
}
