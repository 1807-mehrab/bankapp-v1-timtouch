package com.revature.transactions;

import com.revature.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public interface Transferable
{
    public void transfer(BigDecimal amount, int targetBankAccountNumber) throws InvalidAmountException;
}
