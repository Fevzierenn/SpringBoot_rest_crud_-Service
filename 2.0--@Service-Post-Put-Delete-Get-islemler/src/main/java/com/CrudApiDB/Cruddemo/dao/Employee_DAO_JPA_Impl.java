package com.CrudApiDB.Cruddemo.dao;

import com.CrudApiDB.Cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository     //database'den veri alma çekme işlemlerinin yapılacağı class olduğu için @Repository Anotasyonu konuldu.
public class Employee_DAO_JPA_Impl implements IEmployeeDAO{
    //define field for EntityManager
    private EntityManager entityManager;
    //create constructor for Dependency injection
    @Autowired
    public Employee_DAO_JPA_Impl(EntityManager theEntityManager)        //Spring boot tarafından otomatik olarak yaratılır.
    {
        this.entityManager=theEntityManager;        //(Constructor İnjection.)
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> theQuery=entityManager.createQuery("From Employee",Employee.class);
        //execute query and getResultList
        List<Employee> allEmployees=theQuery.getResultList();
        //return statement
        return allEmployees;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //kayıt edilecek. id,0'dan farklıysa var olan Employee update edilecek.
        Employee dbEmployee=entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        //DB'de id'ye gore eleman ara.
        Employee willBeDeleted = entityManager.find(Employee.class,id);
        //aranilan id yoksa islem yapma
        if(willBeDeleted == null)
            System.out.println(id+" id'ye ait ve calisan yok dolayisiyla silme islemi gerceklestirilemedi.");
        //aranilan id varsa DB'den sil.
        entityManager.remove(willBeDeleted);
    }

    @Override
    public Employee findSingleEmployeebyID(int employeeID) {
        Employee singleEmployee= entityManager.find(Employee.class,employeeID);
        return singleEmployee;
    }
}
