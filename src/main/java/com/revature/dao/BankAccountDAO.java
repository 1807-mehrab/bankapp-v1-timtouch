package com.revature.dao;

import com.revature.beans.BankAccount;
import com.revature.beans.BankClient;
import com.revature.beans.CheckingAccount;
import com.revature.beans.SavingsAccount;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: Method to find all bank clients associated with a bank account
public class BankAccountDAO
{
    private static final String getAllBankAccountsOfBankClientQuery =
            "SELECT * FROM BANKCLIENTBANKACCOUNT INNER JOIN BANKACCOUNT " +
            "ON BANKCLIENTBANKACCOUNT.BANKACCOUNT_ID = BANKACCOUNT.BANKACCOUNT_ID " +
            "INNER JOIN BANKACCOUNTTYPE ON BANKACCOUNT.BANKACCOUNTTYPE_ID = BANKACCOUNTTYPE.BANKACCOUNTTYPE_ID " +
            "WHERE BANKCLIENTBANKACCOUNT.BANKCLIENT_ID = ?";
    private static final String getBankAccountByIdQuery =
            "SELECT * FROM BANKACCOUNT INNER JOIN BANKACCOUNTTYPE " +
            "ON BANKACCOUNT.BANKACCOUNTTYPE_ID = BANKACCOUNTTYPE.BANKACCOUNTTYPE_ID WHERE BANKACCOUNT_ID = ?";
    private static final String getBankAccountByAccountNumberQuery =
            "SELECT * FROM BANKACCOUNT INNER JOIN BANKACCOUNTTYPE " +
            "ON BANKACCOUNT.BANKACCOUNTTYPE_ID = BANKACCOUNTTYPE.BANKACCOUNTTYPE_ID WHERE BANKACCOUNT_NUMBER = ?";
    private static final String createSavingsAccountQuery = "INSERT INTO BANKACCOUNT(BANKACCOUNT_NAME, BANKACCOUNT_NUMBER, INTEREST_RATE, BANKACCOUNTTYPE_ID) VALUES(?,?,?,2)";
    private static final String createCheckingAccountQuery = "INSERT INTO BANKACCOUNT(BANKACCOUNT_NAME, BANKACCOUNT_NUMBER, MINIMUM_BALANCE, BANKACCOUNTTYPE_ID) VALUES(?,?,?,1)";
    private static final String createBankClientBankAccountQuery = "INSERT INTO BANKCLIENTBANKACCOUNT (BANKCLIENT_ID, BANKACCOUNT_ID) VALUES (?,?)";
    private static final String updateBankAccountNameQuery = "UPDATE FROM BANKACCOUNT SET BANKACCOUNT_NAME = ? WHERE BANKACCOUNT_ID = ?";


    public List<BankAccount> getBankClientBankAccounts(int bankClientId){
        PreparedStatement ps;
        BankAccount bankAccount = null;

        List<BankAccount> bankAccounts = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getAllBankAccountsOfBankClientQuery);
            ps.setInt(1,bankClientId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                bankAccount = createBankAccountInstance(rs);

                bankAccounts.add(bankAccount);

            }

            rs.close();
            ps.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bankAccounts;
    }

    public BankAccount getBankAccountById(int accountId){
        PreparedStatement ps;

        BankAccount bankAccount = null;

        try(Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankAccountByIdQuery);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                bankAccount = createBankAccountInstance(rs);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bankAccount;
    }

    /**
     * Get BankAccount given an account number
     * @param accountNumber
     * @return
     */
    public BankAccount getBankAccountByAccountNumber(int accountNumber){
        PreparedStatement ps;

        BankAccount bankAccount = null;

        try(Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankAccountByAccountNumberQuery);
            ps.setInt(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                bankAccount = createBankAccountInstance(rs);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bankAccount;
    }


    /**
     * Creates a BankAccount instance from a ResultSet
     * @param rs
     * @return
     */
    private BankAccount createBankAccountInstance(ResultSet rs){
        BankAccount bankAccount = null;
        try
        {
            int id = rs.getInt("bankaccount_id");
            String accountName = rs.getString("bankaccount_name");
            int accountNumber = rs.getInt("bankaccount_number");

            // Used to decide what subclass of BankAccount to instantiate as
            switch (rs.getString("bankaccounttypename"))
            {
                case "CHECKING":
                    BigDecimal minimumBalance = rs.getBigDecimal("minimum_balance");
                    bankAccount = new CheckingAccount(id, accountName, accountNumber, minimumBalance);
                    break;
                case "SAVINGS":
                    double interestRate = rs.getDouble("interest_rate");
                    bankAccount = new SavingsAccount(id, accountName, accountNumber, interestRate);
                    break;
                default:
                    System.out.println("Did not find a type for bank account.");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return bankAccount;
    }

    /**
     * Creates a checking account in the database and adds a relationship to it's owner
     * @param bankClient
     * @param bankAccount
     */
    public void createCheckingAccount(BankClient bankClient, CheckingAccount bankAccount){
        PreparedStatement ps;

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(createCheckingAccountQuery);
            ps.setString(1, bankAccount.getBankAccountName());
            ps.setInt(2, bankAccount.getBankAccountNumber());
            ps.setBigDecimal(3, bankAccount.getMinimumBalance());

            ps.executeUpdate();

            // Also add the relationship to represent the bank client owning the new bank account
            ps = conn.prepareStatement(createBankClientBankAccountQuery);

            ps.setInt(1, bankClient.getId());
            ps.setInt(2, getBankAccountByAccountNumber(bankAccount.getBankAccountNumber()).getId());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Creates a savings account in the database and adds a relationship to it's owner
     * @param bankClient
     * @param bankAccount
     */
    public void createSavingsAccount(BankClient bankClient, SavingsAccount bankAccount){
        PreparedStatement ps;

        try (Connection conn = ConnectionUtil.getConnection())
        {

            ps = conn.prepareStatement(createSavingsAccountQuery);
            ps.setString(1, bankAccount.getBankAccountName());
            ps.setInt(2, bankAccount.getBankAccountNumber());
            ps.setDouble(3, bankAccount.getInterestRate());

            ps.executeUpdate();


            // Also add the relationship to represent the bank client owning the new bank account
            ps = conn.prepareStatement(createBankClientBankAccountQuery);

            ps.setInt(1, bankClient.getId());
            ps.setInt(2, getBankAccountByAccountNumber(bankAccount.getBankAccountNumber()).getId());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Update an account given it's id
     * @param accountId
     * @param accountName
     */
    public void updateBankAccountName (int accountId, String accountName){
        PreparedStatement ps;

        try(Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(updateBankAccountNameQuery);
            ps.setString(1, accountName);
            ps.setInt(2, accountId);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
