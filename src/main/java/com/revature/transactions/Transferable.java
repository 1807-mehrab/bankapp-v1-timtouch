package com.revature.transactions;

import com.revature.exceptions.InvalidAmountException;

public interface Transferable
{
    public void transfer(double amount, int sourceBankAccountNumber, int targetBankAccountNumber) throws InvalidAmountException;
}
