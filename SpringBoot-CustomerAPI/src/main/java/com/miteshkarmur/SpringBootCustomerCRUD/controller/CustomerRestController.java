package com.miteshkarmur.SpringBootCustomerCRUD.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miteshkarmur.SpringBootCustomerCRUD.entity.Customer;
import com.miteshkarmur.SpringBootCustomerCRUD.entity.CustomerErrorResponse;
import com.miteshkarmur.SpringBootCustomerCRUD.exceptions.CustomerNotFoundException;
import com.miteshkarmur.SpringBootCustomerCRUD.service.CustomerService;

@RestController
@RequestMapping("/api")
class CustomerRestController {

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
	
	/*@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
		CustomerErrorResponse cError=new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), 
																ex.getMessage(), 
																System.currentTimeMillis());
		return new ResponseEntity<>(cError, HttpStatus.NOT_FOUND);
	}*/
	
	@GetMapping(value = "/healthcheck")
    public HeathCheck healthcheck(@RequestParam("format") String format) {
		if(format.equals("short")) {
			return new HealthCheckShort("OK");
		}else if (format.equals("full")) {
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
			df.setTimeZone(tz);
			String nowAsISO = df.format(new Date());
			return new HealthCheckLong(nowAsISO, "OK");
		}else {
			throw new BadException();
		}
        
    }

    @PutMapping(value = "/healthcheck")
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void healthcheckPut() {
    	return;
    }

    @PostMapping(value = "/healthcheck")
    public ResponseEntity<Object> healthcheckPost() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }


    @DeleteMapping(value = "/healthcheck")
    public ResponseEntity<Object> healthcheckDelete() {
    	return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
class HeathCheck{
	
}

class HealthCheckShort extends HeathCheck{
	String status;

	public HealthCheckShort() {
		
	}

	public HealthCheckShort(String status) {
		this.status = status;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

class HealthCheckLong extends HeathCheck{
	String currentTime;
	String application;
	public HealthCheckLong() {
		
	}
	public HealthCheckLong(String currentTime, String application) {
		this.currentTime = currentTime;
		this.application = application;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	
}
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BadException extends RuntimeException{

	public BadException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public BadException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public BadException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public BadException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
}

