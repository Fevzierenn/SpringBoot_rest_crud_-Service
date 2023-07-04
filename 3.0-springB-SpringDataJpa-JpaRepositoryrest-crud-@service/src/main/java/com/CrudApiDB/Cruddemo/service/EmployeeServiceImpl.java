package com.CrudApiDB.Cruddemo.service;

import com.CrudApiDB.Cruddemo.dao.IEmployeeRepository;
import com.CrudApiDB.Cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private IEmployeeRepository employeeRepository;       //service aracılığıyla dao bağlanıldı. @Service aracı oldu yani.

    @Autowired      //dependency injection icin kullanılan anotasyondur.
    public EmployeeServiceImpl(IEmployeeRepository theEmployeeDAO)
    {
        this.employeeRepository=theEmployeeDAO;
    }


    @Override
    public Employee findById(int employeeID) {
        Optional<Employee> result = employeeRepository.findById(employeeID);
        Employee theEmployee=null;
        if(result.isPresent())
        {
            theEmployee=result.get();
        }
        else
        {
            //we didnt find any employee.
            throw new RuntimeException("there is no employee having a id:"+employeeID);
        }
        return theEmployee;
    }

    //@Transactional      //Artık gerek yok. Jpa repository bunu bizim icin yapmakta.
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    //@Transactional      //Database'de degisiklik var. Okuma yok.
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
