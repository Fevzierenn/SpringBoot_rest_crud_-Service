package com.CrudApiDB.Cruddemo.rest;


import com.CrudApiDB.Cruddemo.dao.IEmployeeDAO;
import com.CrudApiDB.Cruddemo.entity.Employee;
import com.CrudApiDB.Cruddemo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private IEmployeeService employeeService;

    @Autowired
    public EmployeeRestController(IEmployeeService dbBaglanacakSinif)
    {
        this.employeeService=dbBaglanacakSinif;
    }
    @GetMapping("/employees")
    public List<Employee> allEmployees()
    {
        return employeeService.findAll();
    }

    /*
    //DAO injection yapılmalı(şimdilik burada ctor injection ile yapılacak.)
    private IEmployeeDAO employeeDAO;

    @Autowired
    public EmployeeRestController(IEmployeeDAO dbBaglanacakSinif)
    {
        this.employeeDAO=dbBaglanacakSinif;
    }

    @GetMapping("/employees")
    public List<Employee> allEmployees()
    {
        return employeeDAO.findAll();
    }

     */
}
