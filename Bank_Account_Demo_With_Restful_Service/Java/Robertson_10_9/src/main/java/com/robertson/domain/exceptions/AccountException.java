package com.robertson.domain.exceptions;

/**
 *     Base class exception for all customer exceptions that are thrown by Bank Account derived objects.
 */
public class AccountException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8986637733837690532L;

	public AccountException(String message) {
        super(message);
    }
}
