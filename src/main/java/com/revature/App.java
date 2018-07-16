package com.revature;

import com.revature.beans.BankAccount;
import com.revature.beans.BankClient;
import com.revature.dao.BankClientDAO;

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

        BankClient bankClient = bankClientDAO.getBankClientById(1);


        for(BankAccount account: bankClient.getAllBankAccounts()){
            System.out.println(account);
        }


    }

}
