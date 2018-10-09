package com.miteshkarmur.SpringBootCustomerCRUD.service;

import java.util.List;
import com.miteshkarmur.SpringBootCustomerCRUD.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(Integer id);

	public void saveOrUpdateCustomer(Customer customer);
	
	public void deleteCustomer(Customer customer);
}
