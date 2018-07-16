package com.revature;

import com.revature.beans.BankAccount;
import com.revature.beans.BankClient;
import com.revature.beans.Transaction;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.BankClientDAO;
import com.revature.dao.TransactionDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Banking app in which users can login in to their transactions and access their bank accounts.
 * If they don't have a bank transactions, they can create one.
 *
 */
public class App 
{
    Scanner scanner = new Scanner(System.in);
    String input;
    Map<Integer, String> options = new HashMap<Integer, String>();

    boolean quit;


    public static void main( String[] args )
    {
        BankClientDAO bankClientDAO = new BankClientDAO();
        BankAccountDAO bankAccountDAO = new BankAccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        BankClient bankClient = bankClientDAO.getBankClientById(1);

        BankAccount bankAccount = bankAccountDAO.getBankAccountById(1);

        for (Transaction t:
                transactionDAO.getAllTransactionsFromBankAccount(bankAccount.getId()))
        {
            System.out.println(t);
        }

        System.out.println(bankAccount.getBalance());
//        for(BankAccount account: bankClient.getAllBankAccounts()){
//            System.out.println(account);
//        }


    }

}
