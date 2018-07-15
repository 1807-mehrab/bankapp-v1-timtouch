package com.revature;

import com.revature.beans.SavingsAccount;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class BankAccountTest
{
    SavingsAccount savingsAccount;

    @Before
    public void initializeBankAccount()
    {
        savingsAccount = new SavingsAccount(12345, 1000);
    }

//    @Test
//    public void successfullyWithdrawFromSavingsAccountTest()
//    {
//        try
//        {
//            savingsAccount.withdraw(100.00);
//            assertEquals(900, savingsAccount.getBalance(), 0.001);
//
//        } catch (InvalidAmountException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void overdrewFromSavingsAccountTest()
//    {
//        try
//        {
//            savingsAccount.withdraw(1001);
//            assertEquals(1000, savingsAccount.getBalance(), 0.001);
//        } catch (InvalidAmountException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void negativeWithdrawValueFromSavingsAccountTest()
//    {
//        try
//        {
//            savingsAccount.withdraw(-1);
//            assertEquals(1000, savingsAccount.getBalance(), 0.001);
//        } catch (InvalidAmountException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void successfullyDepositIntoSavingsAccountTest()
//    {
//        try
//        {
//            savingsAccount.deposit(100);
//            assertEquals(1100, savingsAccount.getBalance(), 0.001);
//        } catch (InvalidAmountException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @Test
//    public void negativeDepositValueIntoSavingsAccountTest()
//    {
//        try
//        {
//            savingsAccount.deposit(-100);
//            assertEquals(1000, savingsAccount.getBalance(), 0.001);
//        } catch (InvalidAmountException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }


}
