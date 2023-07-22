package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService theService) {
		employeeService = theService;
	}


	/*----------------------------------------------------In memory. Biz Database'den getirtecegiz.
	private List<Employee> theEmployees;
	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
	*///-------------------------------------------------------------------------------------------------------

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> tumEmployeeler = employeeService.findAll();
		theModel.addAttribute("employees", tumEmployeeler);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String getFormForAdd(Model model) {
		Employee theEmployee = new Employee();
		model.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}



	@PostMapping("/save")
	public String saveTheEmployee(@ModelAttribute("employee") Employee theEmployee)    //html page'inde th:object="${employee}".
	{
		//save the Employee to the DB.
		employeeService.save(theEmployee);
		//Employee listesine geri don.
		return "redirect:/employees/list";
	}


	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theID,Model theModel)
	{
		//get the specific employee from service using the ıd
		Employee singleEmployee=employeeService.findById(theID);
		//set employee in the model to prepopulate the form
		theModel.addAttribute("employee",singleEmployee);

		//send over to our form(Update formuna id'si ile bulunan employee modelini yolla)
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId)
	{
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
}









