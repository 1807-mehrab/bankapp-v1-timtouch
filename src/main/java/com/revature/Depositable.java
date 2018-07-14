package com.revature;

import com.revature.exceptions.InvalidAmountException;

public interface Depositable
{
    public void deposit(double amount) throws InvalidAmountException;
}
