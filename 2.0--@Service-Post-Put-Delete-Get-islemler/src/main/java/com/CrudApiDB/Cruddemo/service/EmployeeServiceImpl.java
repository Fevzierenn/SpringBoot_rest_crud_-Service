package com.CrudApiDB.Cruddemo.service;

import com.CrudApiDB.Cruddemo.dao.IEmployeeDAO;
import com.CrudApiDB.Cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
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
    public Employee findSingleEmployeebyID(int employeeID) {
        return employeeDAO.findSingleEmployeebyID(employeeID);
    }

    @Transactional      //normalde EmployeeDao'da olmalıydı fakat @Service olunca burada tanımlandı.
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional      //Database'de degisiklik var. Okuma yok.
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
}
