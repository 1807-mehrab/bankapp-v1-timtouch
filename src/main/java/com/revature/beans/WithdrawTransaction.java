package com.revature.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawTransaction extends Transaction
{
    public WithdrawTransaction(BigDecimal amount, int sourceBankAccountId)
    {
        super(amount, sourceBankAccountId);
    }

    public WithdrawTransaction(int id, BigDecimal amount, LocalDateTime timeOfTransaction, int sourceBankAccountId)
    {
        super(id, amount, timeOfTransaction, sourceBankAccountId);
    }

    @Override
    public String toString()
    {
        return "WithdrawTransaction{} " + super.toString();
    }
}
