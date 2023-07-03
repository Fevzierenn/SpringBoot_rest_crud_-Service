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
}
