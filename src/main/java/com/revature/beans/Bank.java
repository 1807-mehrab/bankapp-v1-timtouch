package com.revature.beans;

import com.revature.dao.BankDAO;

import java.util.HashSet;
import java.util.Set;

/**
 * The Bank class handles creating new clients and bank accounts?
 * <p>
 * Employs the Singleton Pattern to make sure only one instance of Bank is created at all times (May want to change if your app can interface with multiple banks)
 */
public class Bank
{
    private int id;
    private String name;
    private Set<BankClient> bankClients = new HashSet<BankClient>();

    private BankDAO bankDAO = new BankDAO();

    public Bank()
    {
    }

    public Bank(String name)
    {
        this.name = name;
    }

    public Bank(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * Adds a new client to the bank
     *
     * @param newClient client to be added to the bank
     */
    public void addNewClient(BankClient newClient)
    {
        bankClients.add(newClient);
    }


    /**
     * Finds a bank transactions by the transactions number
     *
     * @param bankAccountNumber is the bank transactions number
     * @return the matching BankAccount object, <code>null</code> if none are found
     */
    public BankAccount findBankAccount(int bankAccountNumber)
    {
        BankAccount foundBankAccount;
        for (BankClient client : bankClients)
        {
            foundBankAccount = client.getBankAccount(bankAccountNumber);
            if (foundBankAccount != null)
            {
                return foundBankAccount;
            }
        }
        return null;
    }

    //////////////////////////////////////////////////
    // GETTERS AND SETTERS

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
