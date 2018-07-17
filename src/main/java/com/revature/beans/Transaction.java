package com.revature.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public abstract class Transaction
{
    private int id;
    private BigDecimal amount;
    private LocalDateTime timeOfTransaction;
    private int sourceBankAccountId;

    public Transaction() {}

    public Transaction(BigDecimal amount, int sourceBankAccountId)
    {
        this.amount = amount;
        this.sourceBankAccountId = sourceBankAccountId;
    }

    public Transaction(int id, BigDecimal amount, LocalDateTime timeOfTransaction, int sourceBankAccountId)
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

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
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
