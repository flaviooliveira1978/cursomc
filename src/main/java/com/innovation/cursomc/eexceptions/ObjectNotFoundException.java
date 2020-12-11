package com.innovation.cursomc.eexceptions;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ObjectNotFoundException(String m) {
		super(m);
		
	}
	
	public ObjectNotFoundException(String m, Throwable t) {
		super(m,t);
	}
}
