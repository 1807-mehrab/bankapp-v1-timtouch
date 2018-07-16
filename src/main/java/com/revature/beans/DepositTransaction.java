package com.revature.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositTransaction extends Transaction
{
    public DepositTransaction(BigDecimal amount, int sourceBankAccountId)
    {
        super(amount, sourceBankAccountId);
    }

    public DepositTransaction(int id, BigDecimal amount, LocalDateTime timeOfTransaction, int sourceBankAccountId)
    {
        super(id, amount, timeOfTransaction, sourceBankAccountId);
    }

    @Override
    public String toString()
    {
        return "DepositTransaction{} " + super.toString();
    }
}
