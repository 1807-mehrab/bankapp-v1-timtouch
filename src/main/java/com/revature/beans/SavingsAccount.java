package com.revature.beans;

public class SavingsAccount extends BankAccount
{
    private double interestRate;

    public SavingsAccount(String bankAccountName, int bankAccountNumber, double interestRate)
    {
        super(bankAccountName, bankAccountNumber);
        this.interestRate = interestRate;
    }

    public SavingsAccount(int id, String bankAccountName, int bankAccountNumber, double interestRate)
    {
        super(id, bankAccountName, bankAccountNumber);
        this.interestRate = interestRate;
    }

    //////////////////////////////////////////////////
    // GETTERS AND SETTERS

    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(float interestRate)
    {
        this.interestRate = interestRate;
    }

    @Override
    public String toString()
    {
        return "SavingsAccount{" +
                "interestRate=" + interestRate +
                "} " + super.toString();
    }
}
