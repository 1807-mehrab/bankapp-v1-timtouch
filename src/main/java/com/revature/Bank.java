package com.revature;

import java.util.HashSet;
import java.util.Set;

/**
 * The Bank class handles creating new clients and bank accounts?
 *
 * Employs the Singleton Pattern to make sure only one instance of Bank is created at all times (May want to change if your app can interface with multiple banks)
 */
public class Bank
{
    private static Bank instance = new Bank();
    private Set<BankClient> bankClients = new HashSet<BankClient>();

    private Bank() {} // Private so you can't create other instances

    /**
     * Method to access the Bank singleton
     * @return the Bank singleton
     */
    public static Bank getInstance() {
        return instance;
    }

    /**
     * Adds a new client to the bank
     * @param newClient client to be added to the bank
     */
    public void addNewClient(BankClient newClient){
        bankClients.add(newClient);
    }

    /**
     * Finds a bank account by the account number
     * @param bankAccountNumber is the bank account number
     * @return the matching BankAccount object, <code>null</code> if none are found
     */
    public BankAccount findBankAccount(int bankAccountNumber){
        BankAccount foundBankAccount;
        for (BankClient client : bankClients){
            foundBankAccount = client.getBankAccount(bankAccountNumber);
            if (foundBankAccount != null){
                return foundBankAccount;
            }
        }
        return null;
    }

}
