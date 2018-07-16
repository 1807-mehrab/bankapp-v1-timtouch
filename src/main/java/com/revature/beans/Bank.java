package com.revature.beans;

import com.revature.dao.BankDAO;

import java.util.HashSet;
import java.util.Set;

/**
 * The Bank class contain info about a bank
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
