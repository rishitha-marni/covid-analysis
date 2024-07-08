package com.mindtree.exception;

public class NoDataFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5216656223318798108L;
 
	public NoDataFoundException(String errorMsg) {
        super(errorMsg);
	}
}
