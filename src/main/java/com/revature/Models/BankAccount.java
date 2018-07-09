package com.revature.Models;

import com.revature.Exceptions.InvalidAmountException;

/**
 * The abstract BankAccount class is used as a basis for things a bank account can do.
 */
public abstract class BankAccount
{
    private String bankAccountName;
    private int bankAccountNumber;
    private double balance;
    private boolean isClosed;

    /**
     * Withdraws money from the bank account's balance.
     * @param amount amount to be withdrawn.
     */
    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract void transfer(double amount, int bankAccountNumber);

    public void closeAccount()
    {
        isClosed = true;
    }

    public void openAccount()
    {
        isClosed = false;
    }

    public String getBankAccountName()
    {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName)
    {
        this.bankAccountName = bankAccountName;
    }

    public int getBankAccountNumber()
    {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber)
    {
        this.bankAccountNumber = bankAccountNumber;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
