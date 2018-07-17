package com.revature;

import com.revature.beans.Bank;
import com.revature.beans.BankClient;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.BankClientDAO;
import com.revature.dao.BankDAO;
import com.revature.dao.TransactionDAO;
import com.revature.util.SHA512Hash;

import java.util.Scanner;

/**
 * Banking app in which users can login in to their transactions and access their bank accounts.
 * If they don't have a bank transactions, they can create one.
 *
 */
public class App 
{
    private static LoginSession loginSession = new LoginSession();
    private static BankDAO bankDAO = new BankDAO();
    private static BankClientDAO bankClientDAO = new BankClientDAO();
    private static BankAccountDAO bankAccountDAO = new BankAccountDAO();
    private static TransactionDAO transactionDAO = new TransactionDAO();
    private static Scanner scanner = new Scanner(System.in);

    private static Bank bank;
    private static String input;

    private static boolean quit = false;


    public static void main( String[] args )
    {

        bank = bankDAO.getBank(1);

        do{
            System.out.println("Welcome to " + bank.getName());

            boolean isValidInput = true;
            do{
                System.out.println("What would you like to do?");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Quit");

                input = scanner.nextLine();

                switch (input.trim()){
                    case "1":
                        login();
                        break;
                    case "2":
                        createUserAccount();
                        break;
                    case "3":
                        System.out.println("Good bye.");
                        quit = true;
                        break;
                    default:
                        System.out.println("That is not a valid option. ");
                }

                while(loginSession.isLoggedIn()){
                    System.out.println("What would you like to do?");
                    System.out.println("1. View all your bank accounts");
                    System.out.println("2. View the transactions of your bank account");
                    System.out.println("3. Create a new bank account");
                    System.out.println("4. Make a deposit");
                    System.out.println("5. Make a withdrawal");
                    System.out.println("6. Make a transfer");
                    System.out.println("7. Logout");

                    input = scanner.nextLine();
                    switch (input.trim()){
                        case "1":
                            loginSession.getLoggedInClient().printAllBankAccounts();
                            break;
                        case "2":
                            viewTransactions();
                            break;
                        case "3":
                            createBankAccount();
                            break;
                        case "4":
                            break;
                        case "5":
                            break;
                        case "6":
                            break;
                        case "7":
                            loginSession.logout();
                            break;
                        default:
                            System.out.println("That is not a valid option");
                    }
                }
            } while(!isValidInput || !quit);


        } while(!quit);

    }

    public static void createUserAccount(){
        BankClient newClient = new BankClient();
        newClient.setBankId(bank.getId());
        System.out.println("Let's create a user account!");
        System.out.println("What's your first name?");
        newClient.setFirstName(scanner.nextLine());

        System.out.println("What's your last name?");
        newClient.setLastName(scanner.nextLine());

        System.out.println("What's your email?");
        newClient.setEmail(scanner.nextLine());

        System.out.println("What's your SSN? (We won't tell anyone)");
        newClient.setSSN(scanner.nextInt());
        scanner.nextLine();

        System.out.println("What do you want as your username?");
        newClient.setUsername(scanner.nextLine());

        System.out.println("What do you want as your password?");
        newClient.setPassword(SHA512Hash.getSHA512SecurePassword(scanner.nextLine()));

        newClient.saveNewClient();
    }

    public static void login(){
        System.out.println("Login");
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (loginSession.login(username, password)){
            System.out.println("You are now logged in as " + loginSession.getLoggedInClient().getUsername());
        } else {
            System.out.println("Login failed");
        }
    }

    // TODO:
    public static void createBankAccount(){
        System.out.println("Let's create a new bank account!");
        System.out.println("What type of account are you making?");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");


    }

    public static void viewTransactions(){

    }



}
