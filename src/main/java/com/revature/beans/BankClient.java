package com.revature.beans;

/**
 * The BankClient class contains all relevant information about a bank client
 */
public class BankClient
{
    private int id;
    private int bankId;
    private String firstName;
    private String lastName;
    private String email;
    private int SSN;
    private String username;
    private String password;



    public BankClient(int bankId,String firstName, String lastName, String email, int SSN, String username, String password)
    {
        this.bankId = bankId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.SSN = SSN;
        this.username = username;
        this.password = password;
    }

    public BankClient(int id, int bankId,String firstName, String lastName, String email, int SSN, String username, String password)
    {
        this.id = id;
        this.bankId = bankId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.SSN = SSN;
        this.username = username;
        this.password = password;
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

    public int getBankId()
    {
        return bankId;
    }

    public void setBankId(int bankId)
    {
        this.bankId = bankId;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getSSN()
    {
        return SSN;
    }

    public void setSSN(int SSN)
    {
        this.SSN = SSN;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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
