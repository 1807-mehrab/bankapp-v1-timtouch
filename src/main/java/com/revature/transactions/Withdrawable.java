package com.revature.transactions;

import com.revature.exceptions.InvalidAmountException;

public interface Withdrawable
{
    public void withdraw(double amount) throws InvalidAmountException;
}
