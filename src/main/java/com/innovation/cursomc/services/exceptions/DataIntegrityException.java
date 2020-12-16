package com.innovation.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public DataIntegrityException(String m) {
		super(m);
		
	}
	
	public DataIntegrityException(String m, Throwable t) {
		super(m,t);
	}
}
