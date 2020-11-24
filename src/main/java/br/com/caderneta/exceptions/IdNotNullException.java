package br.com.caderneta.exceptions;

public class IdNotNullException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "O Id não pode ser nulo";
	
	public IdNotNullException(String msg) {
		super(MESSAGE + msg);
	}

}
