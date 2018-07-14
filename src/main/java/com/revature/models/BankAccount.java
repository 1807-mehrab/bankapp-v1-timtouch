package com.revature.models;

import com.revature.Depositable;
import com.revature.Withdrawable;

/**
 * The abstract BankAccount class is used as a basis for things a bank account can do.
 */
public abstract class BankAccount implements Withdrawable, Depositable
{
    private String bankAccountName;
    private int bankAccountNumber;
    private double balance;


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
