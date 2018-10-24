package com.robertson.domain.exceptions;

/**
 * Derives from the base AccountException, Just in case the name of the class was not descriptive enough, the exception
 * is thrown when an account status is set to INACTIVE
 */
public class InactiveAccount extends AccountException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2396581391607049188L;

	public InactiveAccount() {
        super("Account is Inactive -- Please bring account balance above $25 to enable withdrawals again.");
    }
}
