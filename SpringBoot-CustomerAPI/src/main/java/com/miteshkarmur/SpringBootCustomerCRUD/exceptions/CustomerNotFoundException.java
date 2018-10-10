package com.miteshkarmur.SpringBootCustomerCRUD.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CustomerNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CustomerNotFoundException(String arg0) {
		super(arg0);
	}

	public CustomerNotFoundException(Throwable arg0) {
		super(arg0);
	}

}
