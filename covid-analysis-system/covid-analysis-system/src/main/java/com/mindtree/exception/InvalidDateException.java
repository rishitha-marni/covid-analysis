package com.mindtree.exception;

public class InvalidDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4906723374582540819L;

	public InvalidDateException(String errorMsg) {
		super(errorMsg);
	}

}
