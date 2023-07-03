package com.CrudApiDB.Cruddemo.dao;

import com.CrudApiDB.Cruddemo.entity.Employee;

import java.util.List;

public interface IEmployeeDAO {
    List<Employee> findAll();
    Employee findSingleEmployeebyID(int employeeID);
    Employee save(Employee theEmployee);        //update or save.
    void deleteById(int id);
}
