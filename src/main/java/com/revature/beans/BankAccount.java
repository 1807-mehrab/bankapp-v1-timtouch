package com.revature.beans;

import com.revature.transactions.Depositable;
import com.revature.transactions.Withdrawable;

/**
 * The abstract BankAccount class is used as a basis for things a bank transactions can do.
 */
public abstract class BankAccount implements Withdrawable, Depositable
{
    private int id;
    private String bankAccountName;
    private int bankAccountNumber;
    private double balance;




    //////////////////////////////////////////////////
    // GETTERS AND SETTERS

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    @Override
    public String toString()
    {
        return "BankAccount{" +
                "id=" + id +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber=" + bankAccountNumber +
                ", balance=" + balance +
                '}';
    }
}
