package com.spring.app.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.app.main.model.Employee;
import com.spring.app.main.service.EmployeeService;

@Controller
public class MyController {
	static {
		System.out.println("Controller..");
	}

	@RequestMapping("/")
	public String showDashboard() {
		System.out.println("Dashboard in controller..");
		return "WEB-INF/jsps/dashboard.jsp";
	}

	@RequestMapping("/employees")
	public String showEmployees(Model model, EmployeeService employeeService) {// dependency injection
		// I want to pass some data to employees.jsp
		model.addAttribute("msg", "hello");
		model.addAttribute("price", 20000.0);
		Integer[] iarry = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		model.addAttribute("iarry", iarry);
		
		List<Employee> list=employeeService.getList();
		model.addAttribute("list",list);
		
		return "WEB-INF/jsps/employees.jsp";
	}

	@RequestMapping("/colleges")
	public String showColleges(HttpServletRequest request) {
		request.setAttribute("collegeName", "ABC Institute of Tech");
		String[] sarry = new String[] { "ABC Institute of Tech", "Biswajit College of Engineering" };
		request.setAttribute("sarry", sarry);
		return "WEB-INF/jsps/colleges.jsp";
	}

	@RequestMapping("/students")
	public ModelAndView showStudents(ModelAndView mav) {
		mav.addObject("sname", "Harry Potter");
		mav.setViewName("WEB-INF/jsps/students.jsp");

		List<String> list = new ArrayList<>();
		list.add("Biswajit Pradhan");
		list.add("Debasish Pradhan");

		mav.addObject("slist", list);
		return mav;
	}
	@RequestMapping("/add-employee")
	public String readEmployeeDetails(@RequestParam("ename") String name,
			@RequestParam("ecity") String city, @RequestParam("esalary") double salary,HttpServletRequest request) {
		
		Employee e=new Employee();
		e.setName(name);
		e.setCity(city);
		e.setSalary(salary);
		System.out.println(e);
		
		//employee e must go to DB
		
		String cpath= request.getContextPath();
		return "redirect: "+cpath+"/employees";
		
	}
	@RequestMapping("/sample-link")
	public String sampleLink(@RequestParam("name") String name,@RequestParam("city") String city,HttpServletRequest request) {
		System.out.println(name);
		System.out.println(city);
		String cpath= request.getContextPath();
		return "redirect: "+cpath+"/students";
	}

}
