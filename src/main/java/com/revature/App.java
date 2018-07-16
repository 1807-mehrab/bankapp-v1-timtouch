package com.revature;

import com.revature.beans.Transaction;
import com.revature.dao.TransactionDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Banking app in which users can login in to their transactions and access their bank accounts.
 * If they don't have a bank transactions, they can create one.
 * One
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
        TransactionDAO transactionDAO = new TransactionDAO();


        List<Transaction> transactions = transactionDAO.getAllTransactionsFromBankAccount(3);

        for (Transaction t:
             transactions)
        {
            System.out.println(t);
        }

    }

}
