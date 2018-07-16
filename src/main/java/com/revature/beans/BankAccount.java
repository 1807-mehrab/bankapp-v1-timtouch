package com.revature.beans;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.TransactionDAO;
import com.revature.transactions.Depositable;
import com.revature.transactions.Transferable;
import com.revature.transactions.Withdrawable;

/**
 * The abstract BankAccount class is used as a basis for all types of bank accounts.
 */
public abstract class BankAccount implements Depositable, Withdrawable, Transferable
{
    private int id;
    private String bankAccountName;
    private int bankAccountNumber;

    protected BankAccountDAO bankAccountDAO = new BankAccountDAO();
    protected TransactionDAO transactionDAO = new TransactionDAO();

    public BankAccount()
    {
    }

    public BankAccount(String bankAccountName, int bankAccountNumber)
    {
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
    }

    public BankAccount(int id, String bankAccountName, int bankAccountNumber)
    {
        this.id = id;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
    }

    // TODO: Create query to calculate balance
//    public BigDecimal getBalance()
//    {
//        return balance;
//    }


    /**
     * Saves new bank account
     * @param bankClient - Bank client associated with bank account
     * @return
     */
    public abstract boolean saveNewBankAccount(BankClient bankClient);

    public boolean isValidAccount()
    {
        if(bankAccountDAO.getBankAccountByAccountNumber(getBankAccountNumber()) != null){
            System.out.println("We assigned you a bank account number that already exists. Reassigning account number...");
            // TODO: Reassign account numbers that are duplicates
            return false;
        }
        if(getBankAccountName().isEmpty()){
            System.out.println("Account name cannot be empty.");
            return false;
        }
        return false;
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

    public String getBankAccountName()
    {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName)
    {
        this.bankAccountName = bankAccountName;
    }

    public int getBankAccountNumber()
    {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber)
    {
        this.bankAccountNumber = bankAccountNumber;
    }



    @Override
    public String toString()
    {
        return "BankAccount{" +
                "id=" + id +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber=" + bankAccountNumber +
                '}';
    }
}
