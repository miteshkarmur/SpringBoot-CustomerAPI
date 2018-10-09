package com.miteshkarmur.SpringBootCustomerCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miteshkarmur.SpringBootCustomerCRUD.entity.Customer;
import com.miteshkarmur.SpringBootCustomerCRUD.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String myCustomers(Model model) {
		customerService.saveOrUpdateCustomer(new Customer("Mitesh", "Karmur", "mk@gmail.com"));
		customerService.saveOrUpdateCustomer(new Customer("Subhash", "Karmur", "sk@gmail.com"));
		customerService.saveOrUpdateCustomer(new Customer("Payal", "Karmur", "pk@gmail.com"));
		return "3 Customers Added";
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomerbyId(@PathVariable Integer customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	@PostMapping("/customers")
	public String saveCustomer( Customer customer) {
		customerService.saveOrUpdateCustomer(customer);
		return "redirect:/listCustomers";
	}
	
	@GetMapping("/updateCustomerForm")
	public String updateCustomerform(@RequestParam("custId") Integer custId,Model model) {
		Customer customer=customerService.getCustomerById(custId);
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("custId") Integer custId) {
		Customer customer=customerService.getCustomerById(custId);
		customerService.deleteCustomer(customer);
		return "redirect:/listCustomers";
	}
}
