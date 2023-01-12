package com.shoping.app.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/cart")
	public String showCart() {
		System.out.println("Dashboard in controller..");
		return "WEB-INF/jsps/cart.jsp";
		}
	@RequestMapping("/categories")
	public String showCategories() {
		System.out.println("Dashboard in controller..");
		return "WEB-INF/jsps/categories.jsp";
		}
	@RequestMapping("/contact")
	public String showContact() {
		System.out.println("Dashboard in controller..");
		return "WEB-INF/jsps/contact.jsp";
		}
	@RequestMapping("/products")
	public String showProducts() {
		System.out.println("Dashboard in controller..");
		return "WEB-INF/jsps/products.jsp";
		}
}
