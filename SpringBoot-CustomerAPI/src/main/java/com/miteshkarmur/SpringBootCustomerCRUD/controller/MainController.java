package com.miteshkarmur.SpringBootCustomerCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miteshkarmur.SpringBootCustomerCRUD.entity.Customer;
import com.miteshkarmur.SpringBootCustomerCRUD.service.CustomerService;

@Controller
//@RequestMapping("/")
public class MainController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String myCustomers(Model model) {
		customerService.saveOrUpdateCustomer(new Customer("Mitesh", "Karmur", "mk@gmail.com"));
		customerService.saveOrUpdateCustomer(new Customer("Subhash", "Karmur", "sk@gmail.com"));
		customerService.saveOrUpdateCustomer(new Customer("Payal", "Karmur", "pk@gmail.com"));
		return "index";
	}
	@GetMapping("/listCustomers")
	public String listCustomers(Model model) {
		List<Customer> custList=customerService.getAllCustomers();
		model.addAttribute("customers",custList);
		return "list-customers";
	}
	
	@GetMapping("/addCustomerForm")
	public String addCustomerForm(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@GetMapping("/updateCustomerForm")
	public String updateCustomerform(@RequestParam("custId") Integer custId,Model model) {
		Customer customer=customerService.getCustomerById(custId);
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveOrUpdateCustomer(customer);
		return "redirect:/listCustomers";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("custId") Integer custId) {
		Customer customer=customerService.getCustomerById(custId);
		customerService.deleteCustomer(customer);
		return "redirect:/listCustomers";
	}
}
