package com.revature.beans;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.TransactionDAO;
import com.revature.exceptions.InvalidAmountException;
import com.revature.transactions.Depositable;
import com.revature.transactions.Transferable;
import com.revature.transactions.Withdrawable;

import java.math.BigDecimal;

/**
 * The abstract BankAccount class is used as a basis for all types of bank accounts.
 */
public abstract class BankAccount implements Depositable, Withdrawable, Transferable
{
    private int id;
    private String bankAccountName;
    private int bankAccountNumber;

    BankAccountDAO bankAccountDAO = new BankAccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

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

    public BigDecimal getBalance()
    {
        BigDecimal balance = new BigDecimal(0);
        for (Transaction transaction:
             transactionDAO.getAllTransactionsFromBankAccount(id))
        {
            if(transaction instanceof DepositTransaction){
                System.out.println("Add " + transaction.getAmount());
                balance = balance.add(transaction.getAmount());
            } else if (transaction instanceof WithdrawTransaction){
                System.out.println("Subtract " + transaction.getAmount());

                balance =balance.subtract(transaction.getAmount());
            } else if ( transaction instanceof TransferTransaction){
                if( transaction.getSourceBankAccountId() == id) {
                    System.out.println("Subtract " + transaction.getAmount());

                    balance = balance.subtract(transaction.getAmount());
                } else if (((TransferTransaction) transaction).getTargetBankAccountId() == id){
                    System.out.println("Add " + transaction.getAmount());

                    balance = balance.add(transaction.getAmount());
                }
            }
        }

        return balance;
    }


    @Override
    public void deposit(BigDecimal amount) throws InvalidAmountException
    {
        if (amount.compareTo(BigDecimal.ZERO) != 1){
            throw new InvalidAmountException("Amount must be a positive number.");
        }

        DepositTransaction deposit = new DepositTransaction(amount, id);

        transactionDAO.createDepositWithdrawTransaction(deposit);

    }

    @Override
    public void withdraw(BigDecimal amount) throws InvalidAmountException
    {
        if (amount.compareTo(BigDecimal.ZERO) != 1){
            throw new InvalidAmountException("Amount must be a positive number.");
        }
        if (amount.compareTo(getBalance()) == 1){
            throw new InvalidAmountException("Withdrawal amount exceeds balance.");
        }

        WithdrawTransaction withdrawal = new WithdrawTransaction(amount, id);

        transactionDAO.createDepositWithdrawTransaction(withdrawal);
    }

    @Override
    public void transfer(BigDecimal amount, int targetBankAccountNumber) throws InvalidAmountException
    {
        if (amount.compareTo(BigDecimal.ZERO) != 1){
            throw new InvalidAmountException("Amount must be a positive number.");
        }
        if (amount.compareTo(getBalance()) == 1){
            throw new InvalidAmountException("Withdrawal amount exceeds balance.");
        }
        BankAccount targetAccount = bankAccountDAO.getBankAccountByAccountNumber(targetBankAccountNumber);
        if ( targetAccount == null){
            System.out.println("No target account with that account number.");
            return;
        }

        TransferTransaction transfer = new TransferTransaction(amount, id, targetAccount.getId());

        transactionDAO.createTransferTransaction(transfer);
    }

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
