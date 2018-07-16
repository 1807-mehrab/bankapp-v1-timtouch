package com.revature.beans;

public class CheckingAccount extends BankAccount
{
    private double minimumBalance;

    public CheckingAccount(String bankAccountName, int bankAccountNumber, double minimumBalance)
    {
        super(bankAccountName, bankAccountNumber);
        this.minimumBalance = minimumBalance;
    }

    public CheckingAccount(int id, String bankAccountName, int bankAccountNumber, double minimumBalance)
    {
        super(id, bankAccountName, bankAccountNumber);
        this.minimumBalance = minimumBalance;
    }


    //////////////////////////////////////////////////
    // GETTERS AND SETTERS

    public double getMinimumBalance()
    {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance)
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
