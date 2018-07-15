package com.revature.beans;

import com.revature.exceptions.InvalidAmountException;

public class SavingsAccount extends BankAccount
{
    private float interestRate;

    public SavingsAccount (int bankAccountNumber, double initialBalance){
        setBankAccountNumber(bankAccountNumber);
        setBalance(initialBalance);
    }

    public void withdraw(double amount) throws InvalidAmountException
    {
        if ( amount < 0.0d ) {
          throw new InvalidAmountException("Can't withdraw negative dollars!");
        } else if ( amount > getBalance()){
            throw new InvalidAmountException("Can't withdraw more than the balance!");
        } else {
            setBalance( getBalance() - amount);
            System.out.println("Withdrew: " + amount);
            System.out.println("Balance is now: " + getBalance());
        }
    }

    public void deposit(double amount) throws InvalidAmountException
    {
        if ( amount < 0.0d ) {
            throw new InvalidAmountException("Can't deposit negative dollars!");
        } else {
            setBalance(getBalance() + amount);
            System.out.println("Deposited: " + amount);
            System.out.println("Balance is now: " + getBalance());
        }
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
