package com.revature;

import static org.junit.Assert.assertEquals;

import com.revature.Models.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BankAccountTest
{
    SavingsAccount savingsAccount = new SavingsAccount();

    @Before
    public void initializeBankAccount()
    {
        savingsAccount.setBalance(1000.0);
    }

    @Test
    public void successfullyWithdrawFromSavingsAccountTest()
    {
        savingsAccount.withdraw(100.00);
        assertEquals(900, savingsAccount.getBalance(), 0.001);
    }

    @Test
    public void overdrewFromSavingsAccountTest() {
        savingsAccount.withdraw(1001);
        assertEquals(1000, savingsAccount.getBalance(), 0.001);
    }

    @Test
    public void negativeWithdrawValueFromSavingsAccountTest() {
        savingsAccount.withdraw(-1);
        assertEquals(1000, savingsAccount.getBalance(), 0.001);
    }

    @Test
    public void successfullyDepositIntoSavingsAccountTest() {
        savingsAccount.deposit(100);
        assertEquals(1100, savingsAccount.getBalance(), 0.001);
    }

    @Test
    public void negativeDepositValueIntoSavingsAccountTest(){
        savingsAccount.deposit(-100);
        assertEquals(1000, savingsAccount.getBalance(), 0.001);
    }


}
