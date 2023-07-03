package com.CrudApiDB.Cruddemo.rest;


import com.CrudApiDB.Cruddemo.dao.IEmployeeDAO;
import com.CrudApiDB.Cruddemo.entity.Employee;
import com.CrudApiDB.Cruddemo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private IEmployeeService employeeService;

    @Autowired      //dependency injection
    public EmployeeRestController(IEmployeeService dbBaglanacakSinif)
    {
        this.employeeService=dbBaglanacakSinif;
    }

    @GetMapping("/employees")   //dbde bulunan tum Employees listelenecek.
    public List<Employee> allEmployees()
    {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeID}")   //id verilerek o id'ye sahip Employee listelenecek.
    public Employee getEmployeeUsingID(@PathVariable int employeeID)
    {
        Employee theEmployee=employeeService.findSingleEmployeebyID(employeeID);
        if(theEmployee == null)
        {
            throw new RuntimeException("Employee id not found for "+employeeID);
        }
        return theEmployee;
    }


    @PostMapping("/employees")  //Api'den gelen json verisine gore DB employee kaydetme islemi
    public Employee addEmployee(@RequestBody Employee jsondanGelenEmployee)
    {
        /*
         jsondanGelenEmployee={
            "firstName":"James",        //id yok. Post edildigi icin manuel 0 girilecek.
            "lastName":"Harden",
            "eMail":"James_Harden13@gmail.com"}
         */

        jsondanGelenEmployee.setId(0);      //eger id=0 olursa bunun DAO'da anlamı yeni bir Employee olusturulacak demektir.
        Employee dbEmployee= employeeService.save(jsondanGelenEmployee);
        return dbEmployee;
    }


    //----------------------------------------------------------------------------UPDATE baslangic
    /*  rest controller icinde yaratılan Employee update ya da save edilmesi.
    @GetMapping("/update")
    public Employee updateOrSave()
    {
        Employee theEmployee=new Employee("lebron","james","lbj@gmail.com");
        //Employee theEmployee2=new Employee();
        return employeeService.save(theEmployee);

    }
    */

    @PutMapping("/employees")       //Api'den gelen json bilgisine gore var olan Employee'yi update etme islemi.
    public Employee updateEmployee(@RequestBody Employee jsondanGelenEmployee)
    {
        /*
                jsondanGelenEmployee={
            "id":21,            //id degeri girilmis cunku varolan bir deger update edilecektir.
            "firstName":"James",
            "lastName":"Harden",
            "eMail":"James_Harden13@gmail.com"}
         */
        Employee dbEmployee= employeeService.save(jsondanGelenEmployee);
        return dbEmployee;
    }
    //----------------------------------------------------------------UPDATE SON



    /*----------------------------------------------------------DELETE baslangic
    @GetMapping("deleteEmployee/{empID}")
    public void deleteEmployee(@PathVariable int empID)
    {
        employeeService.deleteById(empID);
    }
    */
    @DeleteMapping("/employees/{empID}")
    public String deleteExistingEmployee(@PathVariable int empID)
    {
        //once silinecek employee db'den alınır
        Employee empWhoWillDeleted= employeeService.findSingleEmployeebyID(empID);
        //eger nullsa throw exception
        if(empWhoWillDeleted == null)
        {
            throw new RuntimeException("There is a no Employee in DB who has a id="+empID);
        }
        employeeService.deleteById(empID);
        return empID+" id'sine sahip Employee Database'den silindi...";
    }

    //-------------------------------------------------------------------------DELETE SON





    /*
    ----------------------------------------------------------------------------
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


    -----------------------------------------------------
    */

}
