package com.revature;

import com.revature.beans.BankAccount;
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
    private static Scanner scanner = new Scanner(System.in);
    String input;
    Map<Integer, String> options = new HashMap<Integer, String>();

    boolean quit;


    public static void main( String[] args )
    {
        LoginSession loginSession = new LoginSession();
        BankClientDAO bankClientDAO = new BankClientDAO();
        BankAccountDAO bankAccountDAO = new BankAccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

//        BankClient bankClient = bankClientDAO.getBankClientById(1);
//
        BankAccount bankAccount = bankAccountDAO.getBankAccountById(1);
//
//        BankAccount otherAccount = bankAccountDAO.getBankAccountById(3);


        bankAccount.printMostRecentTransactions();
//        System.out.println("Username:");
//
//        String username = scanner.nextLine();
//
//        System.out.println("Password:");
//        String password = scanner.nextLine();
//
//        if(loginSession.login(username,password)){
//            System.out.println("You've logged in as" + loginSession.getLoggedInClient().getUsername());
//
//        } else {
//            System.out.println("You've failed to log in.");
//        }

//        try {
//
//            bankAccount.transfer(new BigDecimal("100"), otherAccount.getBankAccountNumber());
//        } catch (InvalidAmountException e)
//        {
//            e.getMessage();
//        }
//
//
//        for (Transaction t:
//                transactionDAO.getAllTransactionsFromBankAccount(bankAccount.getId()))
//        {
//            System.out.println(t);
//        }

//        System.out.println(bankAccount.getBalance());
//        for(BankAccount account: bankClient.getAllBankAccounts()){
//            System.out.println(account);
//        }


    }
    // TODO:
    public void createUserAccount(){

    }

    // TODO:
    public void login(){

    }

    // TODO:
    public void createBankAccount(){

    }


}
