package com.revature.dao;

import com.revature.beans.DepositTransaction;
import com.revature.beans.Transaction;
import com.revature.beans.TransferTransaction;
import com.revature.beans.WithdrawTransaction;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO
{
    private static final String getAllTransactionsFromBankAccountQuery =
            "SELECT * FROM BANKACCOUNTTRANSACTION INNER JOIN TRANSACTIONTYPE ON BANKACCOUNTTRANSACTION.TRANSACTIONTYPE_ID = TRANSACTIONTYPE.TRANSACTIONTYPE_ID WHERE SOURCEBANKACCOUNT_ID = ? OR TARGETBANKACCOUNT_ID = ?";
    private static final String getMostRecentTransactionsQuery =
            "SELECT * FROM (SELECT * FROM BANKACCOUNTTRANSACTION INNER JOIN TRANSACTIONTYPE " +
                    "ON BANKACCOUNTTRANSACTION.TRANSACTIONTYPE_ID = TRANSACTIONTYPE.TRANSACTIONTYPE_ID WHERE SOURCEBANKACCOUNT_ID = ? OR TARGETBANKACCOUNT_ID = ?" +
                    "ORDER BY TRANSACTION_DATETIME DESC) WHERE ROWNUM <= ? ORDER BY ROWNUM";
    private static final String createDepositWithdrawTransactionQuery = "INSERT INTO BANKACCOUNTTRANSACTION(AMOUNT, SOURCEBANKACCOUNT_ID, TRANSACTIONTYPE_ID) VALUES(?,?,?)";
    private static final String createTransferTransactionQuery = "INSERT INTO BANKACCOUNTTRANSACTION(AMOUNT, SOURCEBANKACCOUNT_ID,TARGETBANKACCOUNT_ID, TRANSACTIONTYPE_ID) VALUES(?,?,?,3)";

    public List<Transaction> getAllTransactionsFromBankAccount(int accountId){
        PreparedStatement ps;
        Transaction transaction = null;

        List<Transaction> transactionList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getAllTransactionsFromBankAccountQuery);
            ps.setInt(1, accountId);
            ps.setInt(2, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transaction = mapTransactionTableToTransactionObject(rs);
                transactionList.add(transaction);

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

        return transactionList;
    }

    public List<Transaction> getMostRecentTransactions(int accountId, int numOfTransaction){
        PreparedStatement ps;
        Transaction transaction = null;

        List<Transaction> transactionList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getMostRecentTransactionsQuery);
            ps.setInt(1, accountId);
            ps.setInt(2, accountId);
            ps.setInt(2, numOfTransaction);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transaction = mapTransactionTableToTransactionObject(rs);
                transactionList.add(transaction);
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

        return transactionList;
    }

    public void createDepositWithdrawTransaction(Transaction transaction){
        // Stop immediately if transaction is not a deposit or withdrawal
        if(transaction instanceof TransferTransaction){
            return;
        }


        PreparedStatement ps;

        try(Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(createDepositWithdrawTransactionQuery);
            ps.setBigDecimal(1, transaction.getAmount());
            ps.setInt(2, transaction.getSourceBankAccountId());

            if(transaction instanceof DepositTransaction){
                ps.setInt(3, 1); // Hardcoded transaction type id here
            } else if(transaction instanceof WithdrawTransaction){
                ps.setInt(3, 2); // Hardcoded transaction type id here
            }

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

    public void createTransferTransaction(TransferTransaction transaction){

        PreparedStatement ps;

        try(Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(createTransferTransactionQuery);
            ps.setBigDecimal(1, transaction.getAmount());
            ps.setInt(2, transaction.getSourceBankAccountId());
            ps.setInt(3, transaction.getTargetBankAccountId());

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


    private Transaction mapTransactionTableToTransactionObject(ResultSet rs){
        Transaction transaction = null;

        try {
            int id = rs.getInt("transaction_id");
            BigDecimal amount = rs.getBigDecimal("amount");
            int sourceBankAccountId = rs.getInt("sourcebankaccount_id");
            int targetBankAccountId = rs.getInt("targetbankaccount_id");
            LocalDateTime transactionTime = rs.getTimestamp("transaction_datetime").toLocalDateTime();
            switch (rs.getString("transactiontype_name")){
                case "DEPOSIT":
                    transaction = new DepositTransaction(id, amount, transactionTime, sourceBankAccountId);
                    break;
                case "WITHDRAW":
                    transaction = new WithdrawTransaction(id, amount, transactionTime, sourceBankAccountId);
                    break;
                case "TRANSFER":
                    transaction = new TransferTransaction(id, amount, transactionTime, sourceBankAccountId, targetBankAccountId);
                    break;
                default:
                    System.out.println("Receive a transaction with no matching transaction type");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return transaction;
    }
}
