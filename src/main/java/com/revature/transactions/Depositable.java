package com.revature.transactions;

import com.revature.exceptions.InvalidAmountException;

import java.math.BigDecimal;

public interface Depositable
{
    public void deposit(BigDecimal amount) throws InvalidAmountException;
}
