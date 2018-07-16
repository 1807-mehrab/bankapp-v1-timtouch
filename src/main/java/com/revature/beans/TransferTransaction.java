package com.revature.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferTransaction extends Transaction
{
    private int targetBankAccountId;

    public TransferTransaction() {}

    public TransferTransaction(BigDecimal amount, int sourceBankAccountId, int targetBankAccountId)
    {
        super(amount, sourceBankAccountId);
        this.targetBankAccountId = targetBankAccountId;
    }

    public TransferTransaction(int id, BigDecimal amount, LocalDateTime timeOfTransaction, int sourceBankAccountId, int targetBankAccountId)
    {
        super(id, amount, timeOfTransaction, sourceBankAccountId);
        this.targetBankAccountId = targetBankAccountId;
    }

    public int getTargetBankAccountId()
    {
        return targetBankAccountId;
    }

    public void setTargetBankAccountId(int targetBankAccountId)
    {
        this.targetBankAccountId = targetBankAccountId;
    }

    @Override
    public String toString()
    {
        return "TransferTransaction{" +
                "targetBankAccountId=" + targetBankAccountId +
                "} " + super.toString();
    }
}
