package com.revature.beans;

import com.revature.transactions.Transferable;

import java.time.LocalDateTime;

public class TransferTransaction extends Transaction implements Transferable
{
    private int targetBankAccountNumber;

    public TransferTransaction(int amount, int sourceBankAccountId, int targetBankAccountNumber)
    {
        super(amount, sourceBankAccountId);
        this.targetBankAccountNumber = targetBankAccountNumber;
    }

    public TransferTransaction(int id, int amount, LocalDateTime timeOfTransaction, int sourceBankAccountId, int targetBankAccountNumber)
    {
        super(id, amount, timeOfTransaction, sourceBankAccountId);
        this.targetBankAccountNumber = targetBankAccountNumber;
    }

    @Override
    public void transfer(double amount, int sourceBankAccountNumber, int targetBankAccountNumber)
    {

    }
}
