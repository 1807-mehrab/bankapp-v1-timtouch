package com.revature.beans;

import com.revature.transactions.Depositable;

import java.time.LocalDateTime;

public class DepositTransaction extends Transaction implements Depositable
{
    public DepositTransaction(int amount, int sourceBankAccountId)
    {
        super(amount, sourceBankAccountId);
    }

    public DepositTransaction(int id, int amount, LocalDateTime timeOfTransaction, int sourceBankAccountId)
    {
        super(id, amount, timeOfTransaction, sourceBankAccountId);
    }

    @Override
    public void deposit(double amount)
    {

    }
}
