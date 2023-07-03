package com.CrudApiDB.Cruddemo.service;

import com.CrudApiDB.Cruddemo.dao.IEmployeeDAO;
import com.CrudApiDB.Cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private IEmployeeDAO employeeDAO;       //service aracılığıyla dao bağlanıldı. @Service aracı oldu yani.

    @Autowired      //dependency injection icin kullanılan anotasyondur.
    public EmployeeServiceImpl(IEmployeeDAO theEmployeeDAO)
    {
        this.employeeDAO=theEmployeeDAO;
    }


    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
