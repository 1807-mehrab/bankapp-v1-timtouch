package com.revature.beans;

public class SavingsAccount extends BankAccount
{
    private double interestRate = 0.01;

    public SavingsAccount() {}

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

    @Override
    public boolean saveNewBankAccount(BankClient bankClient)
    {
        if(isValidAccount()){
            bankAccountDAO.addSavingsAccount(bankClient, this);
            System.out.println("Account successfully made.");
            return true;
        }
        System.out.println("Account creation unsuccessful.");
        return false;

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
