package com.robertson.domain.factories;

import com.robertson.domain.SavingsAccount;
import com.robertson.domain.services.AccountNumberGenerator;
import com.robertson.domain.services.HttpService;

/*
    Simple object factory that returns instances of domain objects
 */
public class DomainObjectFactory {

    // Return new instance of http service
    public static HttpService httpService(){
        return new HttpService();
    }

    // Return the savings account instance.
    public static SavingsAccount savingsAccount(){
        return new SavingsAccount(
                AccountNumberGenerator.generateAccountNumber(),
                25.0,
                0.025
        );
    }
}
