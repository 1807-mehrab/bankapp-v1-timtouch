package com.revature;

import com.revature.dao.BankClientDAO;

import java.util.HashMap;
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
        BankClientDAO bankClientDAO = new BankClientDAO();

        System.out.println(bankClientDAO.getBankClientByUsername("timtouch"));






//        App app = new App();
//        app.initializeOptions();
//        app.start();
    }

    public void initializeOptions(){
        options.put(1,"Check Balance");
        options.put(2, "Withdraw");
        options.put(3, "Deposit");
        options.put(4,"Quit");
    }


    public void start(){


    }




}
