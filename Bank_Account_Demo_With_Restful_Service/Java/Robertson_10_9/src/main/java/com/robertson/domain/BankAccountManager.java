package com.robertson.domain;

import com.robertson.domain.exceptions.AccountException;
import com.robertson.domain.factories.DomainObjectFactory;
import com.robertson.domain.services.HttpService;
/*
    Bank account manager is designed as facade class for the presentation layer to interact with a savings account
 */
public class BankAccountManager {

    private HttpService _httpService;
    private SavingsAccount savingsAccount;

    public BankAccountManager(){
        this.savingsAccount = DomainObjectFactory.savingsAccount();
        this._httpService = DomainObjectFactory.httpService();
    }

    /**
     * Requests that the http perform a post request to the restful service to create a new savings account in the
     * data store.
     * @return Returns a boolean flag if the http request was successful or not
     */
    public boolean createBankAccountInDataStore(){
        return _httpService.postNewAccount(savingsAccount);
    }

    /**
     * Calls the savings account object to perform monthly service charges function.
     * @return Returns a String representation of the current account state information.
     */
    public String performMonthlyServiceCharges() {
        savingsAccount.monthlyProcess();
        updateDataStore();
        return savingsAccount.toString();
    }

    /**
     * Calls the savings account object to perform withdrawal function.
     * @return Returns a String representation of the current account state information.
     */
    public String makeWithdrawal(double withdrawalAmount) throws AccountException {
        savingsAccount.withdraw(withdrawalAmount);
        updateDataStore();
        return savingsAccount.toString();
    }

    /**
     * Calls the savings account object to perform deposit function.
     * @return Returns a String representation of the current account state information.
     */
    public String makeDeposit(double depositAmount) {
        savingsAccount.deposit(depositAmount);
        updateDataStore();
        return savingsAccount.toString();
    }

    /**
     * Calls the savings account object to perform balance inquiry function.
     * @return Returns a String representation of the current account state information.
     */
    public String performBalanceCheck() {
        return savingsAccount.toString();
    }

    // Get the account number of the savings account.
    public int getCurrentAccountNum(){
        return savingsAccount.getAccountNumber();
    }

    // Request the http service update the savings account in the data store.
    private void updateDataStore() {
        _httpService.updateAccount(savingsAccount);
    }
}
