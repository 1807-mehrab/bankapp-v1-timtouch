package com.revature.beans;

import java.time.LocalDateTime;

/**
 * This
 */
public abstract class Transaction
{
    private int id;
    private int amount;
    private LocalDateTime timeOfTransaction;
    private int sourceBankAccountId;

    public Transaction(int amount, int sourceBankAccountId)
    {
        this.amount = amount;
        this.sourceBankAccountId = sourceBankAccountId;
    }

    public Transaction(int id, int amount, LocalDateTime timeOfTransaction, int sourceBankAccountId)
    {
        this.id = id;
        this.amount = amount;
        this.timeOfTransaction = timeOfTransaction;
        this.sourceBankAccountId = sourceBankAccountId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public LocalDateTime getTimeOfTransaction()
    {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(LocalDateTime timeOfTransaction)
    {
        this.timeOfTransaction = timeOfTransaction;
    }

    public int getSourceBankAccountId()
    {
        return sourceBankAccountId;
    }

    public void setSourceBankAccountId(int sourceBankAccountId)
    {
        this.sourceBankAccountId = sourceBankAccountId;
    }

    @Override
    public String toString()
    {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", timeOfTransaction=" + timeOfTransaction +
                ", sourceBankAccountId=" + sourceBankAccountId +
                '}';
    }
}
