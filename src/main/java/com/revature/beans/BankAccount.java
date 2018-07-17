package com.revature.beans;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.TransactionDAO;
import com.revature.exceptions.InvalidAmountException;
import com.revature.transactions.Depositable;
import com.revature.transactions.Transferable;
import com.revature.transactions.Withdrawable;

import java.math.BigDecimal;
import java.util.List;

/**
 * The abstract BankAccount class is used as a basis for all types of bank accounts.
 */
public abstract class BankAccount implements Depositable, Withdrawable, Transferable
{
    BankAccountDAO bankAccountDAO = new BankAccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();
    private int id;
    private String bankAccountName;
    private int bankAccountNumber;


    String printTransactionFormat = "|%-28s|%-15s|%-12s|%-12s|%-10s|";
    String printBorderFormat = "-----------------------------------------------------------------------------------";
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

    /**
     * Prints all transaction that happened in this bank account
     */
    public void printAllTransactions()
    {
        formatPrintTransactions(transactionDAO.getAllTransactionsFromBankAccount(id));
    }

    /**
     * Prints the most recent transactions of this bank account
     */
    public void printMostRecentTransactions()
    {
        formatPrintTransactions(transactionDAO.getMostRecentTransactions(id, 5));
    }

    private void formatPrintTransactions(List<Transaction> transactions){
        System.out.println("ACCOUNT: " + bankAccountName + " (" + bankAccountNumber +")");
        System.out.println(printBorderFormat);
        System.out.println(String.format(printTransactionFormat, "When", "Type","From", "To", "Amount"));
        System.out.println(printBorderFormat);

        for (Transaction t:
                transactions)
        {
            if ( t instanceof DepositTransaction){
                System.out.println(String.format(printTransactionFormat, t.getTimeOfTransaction(), "DEPOSIT", bankAccountDAO.getBankAccountById(t.getSourceBankAccountId()).getBankAccountNumber(), "",t.getAmount()));
            } else if ( t instanceof WithdrawTransaction){
                System.out.println(String.format(printTransactionFormat, t.getTimeOfTransaction(), "WITHDRAW", bankAccountDAO.getBankAccountById(t.getSourceBankAccountId()).getBankAccountNumber(), "",t.getAmount()));

            } else if ( t instanceof  TransferTransaction){
                System.out.println(String.format(printTransactionFormat,
                        t.getTimeOfTransaction(), "TRANSFER", bankAccountDAO.getBankAccountById(t.getSourceBankAccountId()).getBankAccountNumber(), bankAccountDAO.getBankAccountById(((TransferTransaction) t).getTargetBankAccountId()).getBankAccountNumber(),t.getAmount()));

            }

        }
        System.out.println(printBorderFormat);
        System.out.println(String.format("|%-70s|%-10s|", "Balance",getBalance()));
        System.out.println(printBorderFormat);

    }

    public BigDecimal getBalance()
    {
        BigDecimal balance = new BigDecimal(0);
        for (Transaction transaction :
                transactionDAO.getAllTransactionsFromBankAccount(id))
        {
            if (transaction instanceof DepositTransaction)
            {
                balance = balance.add(transaction.getAmount());
            } else if (transaction instanceof WithdrawTransaction)
            {
                balance = balance.subtract(transaction.getAmount());
            } else if (transaction instanceof TransferTransaction)
            {
                if (transaction.getSourceBankAccountId() == id)
                {
                    balance = balance.subtract(transaction.getAmount());
                } else if (((TransferTransaction) transaction).getTargetBankAccountId() == id)
                {
                    balance = balance.add(transaction.getAmount());
                }
            }
        }

        return balance;
    }

    /**
     * Deposits money from this bank account
     *
     * @param amount
     * @throws InvalidAmountException
     */
    @Override
    public void deposit(BigDecimal amount) throws InvalidAmountException
    {
        if (amount.compareTo(BigDecimal.ZERO) != 1)
        {
            throw new InvalidAmountException("Amount must be a positive number.");
        }

        DepositTransaction deposit = new DepositTransaction(amount, id);

        transactionDAO.createDepositWithdrawTransaction(deposit);

    }

    /**
     * Withdraws money from this bank account
     *
     * @param amount
     * @throws InvalidAmountException
     */
    @Override
    public void withdraw(BigDecimal amount) throws InvalidAmountException
    {
        if (amount.compareTo(BigDecimal.ZERO) != 1)
        {
            throw new InvalidAmountException("Amount must be a positive number.");
        }
        if (amount.compareTo(getBalance()) == 1)
        {
            throw new InvalidAmountException("Withdrawal amount exceeds balance.");
        }

        WithdrawTransaction withdrawal = new WithdrawTransaction(amount, id);

        transactionDAO.createDepositWithdrawTransaction(withdrawal);
    }

    /**
     * Transfers money from this bank account to another one
     *
     * @param amount
     * @param targetBankAccountNumber
     * @throws InvalidAmountException
     */
    @Override
    public void transfer(BigDecimal amount, int targetBankAccountNumber) throws InvalidAmountException
    {
        if (amount.compareTo(BigDecimal.ZERO) != 1)
        {
            throw new InvalidAmountException("Amount must be a positive number.");
        }
        if (amount.compareTo(getBalance()) == 1)
        {
            throw new InvalidAmountException("Withdrawal amount exceeds balance.");
        }
        BankAccount targetAccount = bankAccountDAO.getBankAccountByAccountNumber(targetBankAccountNumber);
        if (targetAccount == null)
        {
            throw new IllegalArgumentException("No target account with that account number.");
        }
        if (targetAccount.getId() == id)
        {
            throw new IllegalArgumentException("Cannot make source account the target account.");
        }

        TransferTransaction transfer = new TransferTransaction(amount, id, targetAccount.getId());

        transactionDAO.createTransferTransaction(transfer);
    }

    /**
     * Saves new bank account
     *
     * @param bankClient - Bank client associated with bank account
     * @return
     */
    public abstract boolean saveNewBankAccount(BankClient bankClient);

    public boolean isValidAccount()
    {
        if (bankAccountDAO.getBankAccountByAccountNumber(getBankAccountNumber()) != null)
        {
            System.out.println("We assigned you a bank account number that already exists. Reassigning account number...");
            // TODO: Reassign account numbers that are duplicates
            return false;
        }
        if (getBankAccountName().isEmpty())
        {
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
