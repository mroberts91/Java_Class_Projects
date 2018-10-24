package com.robertson.domain;

import com.robertson.domain.exceptions.AccountException;
import com.robertson.domain.exceptions.InsufficientFunds;

/**
 * Bank Account is designed to be the abstract object in which all account objects are derived from.
 * Contains all the base functionality of a bank account
 */
abstract class BankAccount {

    protected int accountNumber;
    protected double balance;
    protected int depositsThisMonth;
    protected int numberOfWithdrawals;
    protected double annualInterestRate;
    protected double monthlyServiceCharge;

    protected BankAccount(int accountNumber, double balance, double annualInterestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.depositsThisMonth = 0;
        this.numberOfWithdrawals = 0;
        this.monthlyServiceCharge = 0;
    }

    // Perform deposit procedure.
    public void deposit(double depositAmount){
        incrementBalance(depositAmount);
        incrementDeposits(1);
    }

    // Perform withdrawal procedure
    public void withdraw(double withdrawalAmount) throws AccountException {
        if (balance - withdrawalAmount < 0){
            throw new InsufficientFunds();
        }
        decrementBalance(withdrawalAmount);
        incrementWithdrawals(1);
    }

    // Calculate the monthly interest earned and add it to balance.
    protected void calcInterest(){
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        incrementBalance(monthlyInterest);
    }

    // Perform monthly service processing tasks.
    public void monthlyProcess(){
        decrementBalance(monthlyServiceCharge);
        calcInterest();
        resetTransactionCounters();
    }

    private void resetTransactionCounters(){
        setNumberOfWithdrawals(0);
        setDepositsThisMonth(0);
    }

    protected void setMonthlyServiceCharge(double serviceCharge){
        this.monthlyServiceCharge = serviceCharge;
    }

    private void setNumberOfWithdrawals(int i){
        numberOfWithdrawals = i;
    }

    private void setDepositsThisMonth(int i){
        depositsThisMonth = i;
    }

    private void decrementBalance(double d){
        balance -= d;
    }

    private void incrementBalance(double d){
        balance += d;
    }

    private void decrenmentWithdrawlals(int i){
        numberOfWithdrawals -= i;
    }
    private void incrementWithdrawals(int i){
        numberOfWithdrawals += i;
    }
    private void incrementDeposits(int i){
        numberOfWithdrawals += i;
    }
    private void deccrenmentDeposits(int i){
        numberOfWithdrawals -= i;
    }

    // Getters
    public double getBalance() {
        return balance;
    }

    public int getDepositsThisMonth() {
        return depositsThisMonth;
    }

    public int getNumberOfWithdrawals() {
        return numberOfWithdrawals;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

}
