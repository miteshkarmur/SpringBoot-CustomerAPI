package com.miteshkarmur.SpringBootCustomerCRUD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.miteshkarmur.SpringBootCustomerCRUD.entity.CustomerErrorResponse;

@ControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
		CustomerErrorResponse cError=new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), 
																ex.getMessage(), 
																System.currentTimeMillis());
		return new ResponseEntity<>(cError, HttpStatus.NOT_FOUND);
	}
	
	/*@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleGenericException(Exception ex){
		CustomerErrorResponse cError=new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), 
																ex.getMessage(), 
																System.currentTimeMillis());
		return new ResponseEntity<CustomerErrorResponse>(cError, HttpStatus.BAD_REQUEST);
	}*/
	
}
