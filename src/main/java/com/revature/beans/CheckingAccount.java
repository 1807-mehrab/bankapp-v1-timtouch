package com.revature.beans;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount
{
    private BigDecimal minimumBalance;

    public CheckingAccount(String bankAccountName, int bankAccountNumber, BigDecimal minimumBalance)
    {
        super(bankAccountName, bankAccountNumber);
        this.minimumBalance = minimumBalance;
    }

    public CheckingAccount(int id, String bankAccountName, int bankAccountNumber, BigDecimal minimumBalance)
    {
        super(id, bankAccountName, bankAccountNumber);
        this.minimumBalance = minimumBalance;
    }


    //////////////////////////////////////////////////
    // GETTERS AND SETTERS

    public BigDecimal getMinimumBalance()
    {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance)
    {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public String toString()
    {
        return "CheckingAccount{" +
                "minimumBalance=" + minimumBalance +
                "} " + super.toString();
    }
}
