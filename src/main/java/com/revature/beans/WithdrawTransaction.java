package com.revature.beans;

import com.revature.transactions.Withdrawable;

import java.time.LocalDateTime;

public class WithdrawTransaction extends Transaction implements Withdrawable
{
    public WithdrawTransaction(int amount, int sourceBankAccountId)
    {
        super(amount, sourceBankAccountId);
    }

    public WithdrawTransaction(int id, int amount, LocalDateTime timeOfTransaction, int sourceBankAccountId)
    {
        super(id, amount, timeOfTransaction, sourceBankAccountId);
    }

    @Override
    public void withdraw(double amount)
    {

    }
}
