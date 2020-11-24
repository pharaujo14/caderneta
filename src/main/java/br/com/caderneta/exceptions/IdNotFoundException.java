package br.com.caderneta.exceptions;

public class IdNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "O id não foi encontrado";
	
	public IdNotFoundException(String msg) {
		super(MESSAGE + msg);
	}
	
	

}
