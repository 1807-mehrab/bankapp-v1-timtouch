package com.revature.Models;

import com.revature.Depositable;
import com.revature.Exceptions.InvalidAmountException;
import com.revature.Withdrawable;

/**
 * The abstract BankAccount class is used as a basis for things a bank account can do.
 */
public abstract class BankAccount implements Withdrawable, Depositable
{
    private String bankAccountName;
    private int bankAccountNumber;
    private double balance;
    private boolean isClosed;


    public abstract void transfer(double amount, int bankAccountNumber) throws InvalidAmountException;

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
