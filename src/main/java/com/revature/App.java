package com.revature;

import com.revature.Exceptions.InvalidAmountException;
import com.revature.Models.BankAccount;
import com.revature.Models.BankClient;
import com.revature.Models.SavingsAccount;

import java.util.*;

/**
 * Banking app in which users can login in to their account and access their bank accounts.
 * If they don't have a bank account, they can create one.
 * One
 *
 */
public class App 
{
    Scanner scanner = new Scanner(System.in);
    String input;
    Map<Integer, String> options = new HashMap<Integer, String>();

    boolean quit;

    BankClient client = new BankClient("", "", "");

    public static void main( String[] args )
    {
        App app = new App();
        app.initializeOptions();
        app.start();
    }

    public void initializeOptions(){
        options.put(1,"Check Balance");
        options.put(2, "Withdraw");
        options.put(3, "Deposit");
        options.put(4,"Quit");
    }


    public void start(){

        System.out.println( "Welcome to the International Bank of Koalas!" );
        System.out.println("Let's set up your bank account.");

        System.out.println("What is your first name?");
        input = scanner.nextLine();
        client.setFirstName(input);

        System.out.println("What is your last name?");
        input = scanner.nextLine();
        client.setLastName(input);

        System.out.println("What is your email?");
        input = scanner.nextLine();
        client.setEmail(input);


        BankAccount savings = new SavingsAccount(123456, 100);

        client.addBankAccount(savings);


        System.out.println("All right! Looks like you are set up : " + client.getFirstName() + ".");

        quit = false;
        while(!quit){
            System.out.println("What would you like to do next? (Select a number)");
            for (Map.Entry<Integer,String> entry: options.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            input = scanner.nextLine();

            switch (Integer.parseInt(input)){
                case 1:
                    System.out.println("Your balance is: $" + savings.getBalance());
                    break;
                case 2:
                    System.out.println("How much do you want to withdraw?");
                    try {
                        savings.withdraw(scanner.nextInt());
                        scanner.nextLine();
                    } catch (InvalidAmountException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("How much do you want to deposit?");
                    try {
                        savings.deposit(scanner.nextInt());
                        scanner.nextLine();
                    } catch (InvalidAmountException ex) {
                        System.out.println(ex.getMessage());
                    }
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("I'm sorry, that is not a valid selection.");

            }

        }

        System.out.println("Bye!");



    }




}
