package com.revature.dao;

import com.revature.beans.Bank;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO
{
    private static final String getAllBanksQuery = "SELECT * FROM BANK";
    private static final String getBankByIdQuery = "SELECT * FROM BANK WHERE BANK_ID = ?";
    private static final String createBankQuery = "INSERT INTO BANK(BANK_NAME) VALUES(?)";
    private static final String removeBankQuery = "DELETE FROM BANK WHERE BANK_ID = ?";

    /**
     * Get all banks
     * @return
     */
    public List<Bank> getAllBanks(){
        PreparedStatement ps;
        Bank bank;

        List<Bank> banks = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()){
            ps = conn.prepareStatement(getAllBanksQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("bank_id");
                String name = rs.getString("bank_name");

                bank = new Bank(id, name);
                banks.add(bank);
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
        return banks;
    }

    /**
     * Get a bank
     * @param id
     * @return bank with matching id, <code>null</code> if none found
     */
    public Bank getBank(int id){
        PreparedStatement ps;
        Bank bank = null;
        ResultSet rs;

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankByIdQuery);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while(rs.next()){
                int bankId = rs.getInt("bank_id");
                String name = rs.getString("bank_name");

                bank = new Bank(bankId, name);
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
        return bank;
    }

    /**
     * Adds a new bank to database
     * @param bank
     */
    public void addBank(Bank bank) {
        PreparedStatement ps;

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(createBankQuery);

            ps.setString(1,bank.getName());

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
     * Delete bank from database of given id
     * @param bankId
     */
    public void removeBank(int bankId) {
        PreparedStatement ps;

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(removeBankQuery);
            ps.setInt(1,bankId);

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
