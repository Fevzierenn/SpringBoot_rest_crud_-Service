package com.CrudApiDB.Cruddemo.dao;

import com.CrudApiDB.Cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestResource(path = "members")   //endpointi custom ettik yani localhost:8080/api/members ile ulasilabilir oldu
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    //bu kadar. baska kod yazmaya gerek yok.
}
