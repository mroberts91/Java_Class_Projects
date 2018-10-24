package com.robertson.domain.exceptions;

/**
 * Derives from the base AccountException, Just in case the name of the class was not descriptive enough, the exception
 * is thrown when an withdrawal amount is greater than the current balance.
 */
public class InsufficientFunds extends AccountException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4140306599586964294L;

	public InsufficientFunds() {
        super("InsufficientFunds -- You do not have enough money in account to perform this withdrawal!");
    }
}
