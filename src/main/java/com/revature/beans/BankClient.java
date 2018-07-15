package com.revature.beans;

import com.revature.util.SHA512Hash;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The BankClient class contains all relevant information about a bank client
 */
public class BankClient implements Serializable
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;


    private String username;

    // Hashmap of client's bank accounts. K is the bank transactions number, V is the BankAccount object
    private HashMap<Integer, BankAccount> bankAccounts = new HashMap<Integer, BankAccount>();

    private transient int SSN;
    private transient String password;

    public BankClient(String firstName, String lastName, String email, int SSN, String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.SSN = SSN;
        this.username = username;
        this.password = password;
    }

    public void addBankAccount(BankAccount newAccount)
    {
        bankAccounts.put(newAccount.getBankAccountNumber(), newAccount);
    }

    public BankAccount getBankAccount(int bankAccountNumber)
    {
        return bankAccounts.get(bankAccountNumber);
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

    @Override
    public String toString()
    {
        return "BankClient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", SSN=" + SSN +
                ", password='" + password + '\'' +
                '}';
    }
}
