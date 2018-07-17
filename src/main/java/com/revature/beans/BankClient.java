package com.revature.beans;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.BankClientDAO;

import java.util.List;

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

    private BankClientDAO bankClientDAO = new BankClientDAO();
    private BankAccountDAO bankAccountDAO = new BankAccountDAO();


    public BankClient() {}

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

    /**
     * Gets all the client's bank accounts
     * @return
     */
    public List<BankAccount> getAllBankAccounts(){
        return bankAccountDAO.getBankClientBankAccounts(id);
    }

    /**
     * Prints all of client's bank accounts in a formatted fashion
     */
    public void printAllBankAccounts(){

        String printBankAccountFormat = "|%-42s|%-12s|%-15s|%-10s|";
        String border = "------------------------------------------------------------------------------------";

        System.out.println(firstName + "'s bank accounts" );

        System.out.println(border);
        System.out.println(String.format(printBankAccountFormat, "Name", "Account Type", "Account Number", "Balance"));
        System.out.println(border);
        for (BankAccount ba:
             getAllBankAccounts())
        {
            if (ba instanceof CheckingAccount){
                System.out.println(String.format(printBankAccountFormat, ba.getBankAccountName(), "CHECKING", ba.getBankAccountNumber(), ba.getBalance()));
            } else if (ba instanceof SavingsAccount){
                System.out.println(String.format(printBankAccountFormat, ba.getBankAccountName(), "SAVINGS", ba.getBankAccountNumber(), ba.getBalance()));

            }
        }
        System.out.println(border);
    }
    /**
     * Creates and saves a new bank client
     * @return
     */
    public boolean saveNewClient(){
        if(isValidNewUser()) {
            bankClientDAO.addBankClient(this);
            System.out.println("User successfully made.");
            return true;
        }
        System.out.println("User creation unsuccessful.");
        return false;
    }

    /**
     * Checks if this bank client's information is valid
     * @return
     */
    public boolean isValidNewUser(){

        boolean isValid = true;
        if(bankClientDAO.getBankClientByEmail(email) != null){
            System.out.println("Someone with that email already exists.");
            isValid = false;
        }
        if(bankClientDAO.getBankClientBySSN(SSN) != null){
            System.out.println("Someone with that SSN already exists.");
            isValid = false;
        }
        if(bankClientDAO.getBankClientByUsername(username) != null){
            System.out.println("Someone with that username already exists.");
            isValid = false;
        }
        if (firstName.isEmpty()){
            System.out.println("First name cannot be empty");
            isValid = false;
        }
        if(lastName.isEmpty()){
            System.out.println("Last name cannot be empty");
            isValid = false;
        }
        if(SSN < 100000000){
            System.out.println("SSN must be a 9 digit number not starting with 0");
            isValid = false;
        }
        if (username.isEmpty()){
            System.out.println("Username cannot be empty");
            isValid = false;
        }
        if (password.isEmpty()){
            System.out.println("Password cannot be empty");
            isValid = false;
        }
        return isValid;
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
