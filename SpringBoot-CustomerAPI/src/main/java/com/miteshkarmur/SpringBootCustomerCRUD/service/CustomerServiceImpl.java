package com.miteshkarmur.SpringBootCustomerCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miteshkarmur.SpringBootCustomerCRUD.entity.Customer;
import com.miteshkarmur.SpringBootCustomerCRUD.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customerRepo.findById(id).get();
	}

	@Override
	public void saveOrUpdateCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepo.delete(customer);
	}

}
