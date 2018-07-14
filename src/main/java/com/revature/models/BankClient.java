package com.revature.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The BankClient class contains all relevant information about a bank client
 */
public class BankClient implements Serializable
{
    private String firstName;
    private String lastName;
    private String email;


    private String username;

    // Hashmap of client's bank accounts. K is the bank account number, V is the BankAccount object
    private HashMap<Integer, BankAccount> bankAccounts = new HashMap<Integer, BankAccount>();

    private transient int SSN;
    private transient String password;

    public BankClient(String firstName, String lastName, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addBankAccount(BankAccount newAccount)
    {
        bankAccounts.put(newAccount.getBankAccountNumber(), newAccount);
    }

    public BankAccount getBankAccount(int bankAccountNumber)
    {
        return bankAccounts.get(bankAccountNumber);
    }

    // TODO: Create password hashing

    /**
     * Takes in a password, then hashes it and returns a hashed version.
     *
     * @param unhashedPassword password to be hashed.
     * @return the hashed password.
     */
    private String hashPassword(String unhashedPassword)
    {
        return unhashedPassword;
    }


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;

    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getSSN()
    {
        return SSN;
    }

    public void setSSN(int SSN)
    {
        this.SSN = SSN;
    }
}
