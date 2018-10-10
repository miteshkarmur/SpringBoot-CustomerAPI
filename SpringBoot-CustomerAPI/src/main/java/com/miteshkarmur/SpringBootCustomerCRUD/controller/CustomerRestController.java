package com.miteshkarmur.SpringBootCustomerCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miteshkarmur.SpringBootCustomerCRUD.entity.Customer;
import com.miteshkarmur.SpringBootCustomerCRUD.exceptions.CustomerNotFoundException;
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
		Customer cust=customerService.getCustomerById(customerId);
		if(cust==null) {
			throw new CustomerNotFoundException("Customer is not present with Id - "+customerId);
		}
		return cust;
	}
	
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		//customer.setId(0);
		customerService.saveOrUpdateCustomer(customer);
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		Customer cust=customerService.getCustomerById(customer.getId());
		if(cust==null) {
			throw new CustomerNotFoundException("Customer is not present with Id - "+customer.getId());
		}
		customerService.saveOrUpdateCustomer(customer);
		return customer;
		
	}
	
	
	@DeleteMapping("/customers/{custId}")
	public String deleteCustomer(@PathVariable Integer custId) {
		Customer customer=customerService.getCustomerById(custId);
		if(customer==null) {
			throw new CustomerNotFoundException("Customer is not present with Id - "+custId);
		}
		customerService.deleteCustomer(customer);
		return "Deleted customer with Id - "+custId;
	}
}
