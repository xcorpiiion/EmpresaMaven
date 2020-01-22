package br.com.contmatic.services;

public class EmptyStringException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmptyStringException(String message) {
		super(message);
	}

}
