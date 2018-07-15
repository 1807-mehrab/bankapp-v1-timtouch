package com.revature.beans;

import com.revature.transactions.Depositable;
import com.revature.transactions.Withdrawable;

/**
 * The abstract BankAccount class is used as a basis for all types of bank accounts.
 */
public abstract class BankAccount implements Withdrawable, Depositable
{
    private int id;
    private String bankAccountName;
    private int bankAccountNumber;




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

    // TODO: Create query to calculate balance
//    public double getBalance()
//    {
//        return balance;
//    }

    @Override
    public String toString()
    {
        return "BankAccount{" +
                "id=" + id +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber=" + bankAccountNumber +
                '}';
    }
}
