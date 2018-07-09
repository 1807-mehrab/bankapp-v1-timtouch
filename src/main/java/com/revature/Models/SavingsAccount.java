package com.revature.Models;

public class SavingsAccount extends BankAccount
{
    private float interestRate;

    @Override
    public void withdraw(double amount)
    {
        if ( amount < 0.0d ) {
          System.out.println("Can't withdraw negative dollars!");
        } else if ( amount > getBalance()){
            System.out.println("Can't withdraw more than the balanace!");
        } else {
            setBalance( getBalance() - amount);
            System.out.println("Withdrew: " + amount);
            System.out.println("Balance is now: " + getBalance());
        }
    }

    @Override
    public void deposit(double amount)
    {
        if ( amount < 0.0d ) {
            System.out.println("Can't deposit negative dollars!");
        } else {
            setBalance(getBalance() + amount);
            System.out.println("Deposited: " + amount);
            System.out.println("Balance is now: " + getBalance());
        }
    }

    @Override
    public void transfer(double amount, int bankAccountNumber)
    {
        withdraw(amount);
    }

    public float getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(float interestRate)
    {
        this.interestRate = interestRate;
    }
}
