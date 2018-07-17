package com.revature.util;


import com.revature.dao.BankAccountDAO;

public class AccountNumberGenerator
{
    public static int generateValidAccountNumber()
    {
        BankAccountDAO bankAccountDAO = new BankAccountDAO();
        int min = 100000000;
        int max = 999999999;
        int range = max - min + 1;

        int accNum;

        do
        {
            accNum = (int) (Math.random() * range) + min;
        } while (bankAccountDAO.getBankAccountByAccountNumber(accNum) != null);

        return accNum;
    }

}
