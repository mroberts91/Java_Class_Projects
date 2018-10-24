package com.robertson.domain;

import com.robertson.domain.enums.AccountStatus;
import com.robertson.domain.exceptions.AccountException;
import com.robertson.domain.exceptions.InactiveAccount;

/**
 * Savings account is derived from BankAccount and is designed to represent a user's savings account.
 */
public class SavingsAccount extends BankAccount {
    private final double MIN_BALANCE = 25.0;
    private final int MAX_FREE_WITHDRAWLS = 4;
    private final double SERVICE_CHARGE = 1.0;
    private AccountStatus status;

    public SavingsAccount(int accountNumber, double balance, double annualInterestRate) {
        super(accountNumber, balance, annualInterestRate);
        determineAccountStatus();
    }

    // Check account status before making withdrawal, throws exception if inactive
    @Override
    public void withdraw(double withdrawalAmount) throws AccountException {
        if (status == AccountStatus.INACTIVE){
            throw new InactiveAccount();
        }
        super.withdraw(withdrawalAmount);
        determineAccountStatus();
    }

    // Calls the base deposit method and then determines the account status
    @Override
    public void deposit(double depositAmount){
       super.deposit(depositAmount);
       determineAccountStatus();
    }

    // Determines the service charge then calls the base monthlyProcess
    @Override
    public void monthlyProcess(){
        double serviceCharge = determineServiceCharge();
        super.setMonthlyServiceCharge(serviceCharge);
        super.monthlyProcess();
        determineAccountStatus();
    }


    // Calculate the service charges for the account
    private double determineServiceCharge() {
        if (numberOfWithdrawals > MAX_FREE_WITHDRAWLS){
            int chargeableWithdrawals = numberOfWithdrawals - MAX_FREE_WITHDRAWLS;
            return chargeableWithdrawals * SERVICE_CHARGE;
        } else {
            return 0.0;
        }
    }

    private void determineAccountStatus(){
        status = (balance < MIN_BALANCE)? AccountStatus.INACTIVE : AccountStatus.ACTIVE;
    }

    // Getters
    public AccountStatus getStatus() {
        return status;
    }

    // Build a JSON string of the account object
    public String toStringJson(){
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"Id\"")
                .append(":")
                .append(String.valueOf(accountNumber))
                .append(",")
                .append("\"Balance\"")
                .append(":")
                .append(String.valueOf(balance) )
                .append(",")
                .append("\"Deposits\"")
                .append(":")
                .append(String.valueOf(depositsThisMonth) )
                .append(",")
                .append("\"Withdrawals\"")
                .append(":")
                .append(String.valueOf(numberOfWithdrawals) )
                .append(",")
                .append("\"APR\"")
                .append(":")
                .append(String.valueOf(annualInterestRate) )
                .append(",")
                .append("\"Status\"")
                .append(":")
                .append("\"" + status + "\"")
                .append("}");
        return sb.toString();
    }

    // Build string representation for console output.
    @Override
    public String toString() {
       return "\nAccount Information\n" +
               "--------------------\n" +
               "Account Number:\t\t\t" + String.valueOf(accountNumber) + "\n" +
               "Balance:\t\t\t" + String.format("$%.2f", balance) + "\n" +
               "Deposits This Month:\t\t" + String.valueOf(depositsThisMonth) + "\n" +
               "Withdrawals This Month:\t\t" + String.valueOf(numberOfWithdrawals) + "\n" +
               "Account Status:\t\t\t" + status + "\n";
    }
}
