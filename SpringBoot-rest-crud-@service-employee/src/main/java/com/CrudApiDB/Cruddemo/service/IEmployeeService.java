package com.CrudApiDB.Cruddemo.service;

import com.CrudApiDB.Cruddemo.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
}
