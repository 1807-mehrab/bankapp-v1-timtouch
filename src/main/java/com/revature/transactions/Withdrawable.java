package com.revature.transactions;

import com.revature.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public interface Withdrawable
{
    public void withdraw(BigDecimal amount) throws InvalidAmountException;
}
