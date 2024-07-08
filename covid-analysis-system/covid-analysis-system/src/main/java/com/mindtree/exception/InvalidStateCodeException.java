package com.mindtree.exception;

public class InvalidStateCodeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6038392535940525635L;

	public InvalidStateCodeException(String errorMsg) {
        super(errorMsg);
	}
}
