package com.revature;

import com.revature.exceptions.InvalidAmountException;

public interface Transferable
{
    public void transfer(double amount, int bankAccountNumber) throws InvalidAmountException;
}
