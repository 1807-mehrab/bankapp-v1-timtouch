package com.revature.dao;

import com.revature.beans.BankClient;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankClientDAO
{
    private static final String getAllBankClientsFromBankQuery = "SELECT * FROM BANKCLIENT WHERE BANK_ID = ?";
    private static final String getBankClientByIdQuery = "SELECT * FROM BANKCLIENT WHERE BANKCLIENT_ID = ?";
    private static final String getBankClientByEmailQuery = "SELECT * FROM BANKCLIENT WHERE EMAIL = ?";
    private static final String getBankClientBySSNQuery = "SELECT * FROM BANKCLIENT WHERE SSN = ?";
    private static final String getBankClientByUsernameQuery = "SELECT * FROM BANKCLIENT WHERE BANKCLIENT_USERNAME = ?";
    private static final String createBankClientQuery = "INSERT INTO BANKCLIENT(BANK_ID, FIRST_NAME, LAST_NAME, EMAIL, SSN, BANKCLIENT_USERNAME, BANKCLIENT_PASSWORD) VALUES (?,?,?,?,?,?,?)";
    private static final String removeBankClientQuery = "DELETE FROM BANKCLIENT WHERE BANKCLIENT_ID = ?";

    /**
     * Get all clients from given bank
     * @param bankId ID of bank
     * @return a list of clients from the bank
     */
    public List<BankClient> getAllBankClients(int bankId){
        PreparedStatement ps;
        BankClient bankClient;

        List<BankClient> bankClients = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getAllBankClientsFromBankQuery);
            ps.setInt(1,bankId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("bankClient_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int ssn = rs.getInt("SSN");
                String username = rs.getString("bankclient_username");
                String password = rs.getString("bankclient_password");

                bankClient = new BankClient(id, bankId, firstName, lastName, email, ssn, username, password);

                bankClients.add(bankClient);
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
        return bankClients;
    }

    /**
     * Gets a client based on their id
     * @param clientId
     * @return a client if a match is found, <code>null</code> otherwise
     */
    public BankClient getBankClientById(int clientId){
        PreparedStatement ps;
        BankClient bankClient = null;


        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankClientByIdQuery);
            ps.setInt(1,clientId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int bankId = rs.getInt("bank_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int ssn = rs.getInt("SSN");
                String username = rs.getString("bankclient_username");
                String password = rs.getString("bankclient_password");

                bankClient = new BankClient(clientId, bankId, firstName, lastName, email, ssn, username, password);
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
        return bankClient;
    }

    /**
     * Get bank client by their SSN
     * @param ssn
     * @return a client if a match is found, <code>null</code> otherwise
     */
    public BankClient getBankClientBySSN(int ssn){
        PreparedStatement ps;
        BankClient bankClient = null;


        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankClientBySSNQuery);
            ps.setInt(1,ssn);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int clientId = rs.getInt("bankclient_id");
                int bankId = rs.getInt("bank_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String username = rs.getString("bankclient_username");
                String password = rs.getString("bankclient_password");

                bankClient = new BankClient(clientId, bankId, firstName, lastName, email, ssn, username, password);
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
        return bankClient;
    }

    /**
     * Get bank client by email
     * @param email
     * @return a client if a match is found, <code>null</code> otherwise
     */
    public BankClient getBankClientByEmail(String email){
        PreparedStatement ps;
        BankClient bankClient = null;


        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankClientByEmailQuery);
            ps.setString(1,email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int clientId = rs.getInt("bankclient_id");
                int bankId = rs.getInt("bank_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int ssn = rs.getInt("SSN");
                String username = rs.getString("bankclient_username");
                String password = rs.getString("bankclient_password");

                bankClient = new BankClient(clientId, bankId, firstName, lastName, email, ssn, username, password);
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
        return bankClient;
    }


    /**
     * Get bank client by their username
     * @param username
     * @return a client if a match is found, <code>null</code> otherwise
     */
    public BankClient getBankClientByUsername(String username){
        PreparedStatement ps;
        BankClient bankClient = null;


        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(getBankClientByUsernameQuery);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int clientId = rs.getInt("bankclient_id");
                int bankId = rs.getInt("bank_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                int ssn = rs.getInt("SSN");
                String password = rs.getString("bankclient_password");

                bankClient = new BankClient(clientId, bankId, firstName, lastName, email, ssn, username, password);
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
        return bankClient;
    }

    /**
     * Create a new bank client in the database
     * @param bankClient
     */
    public void addBankClient(BankClient bankClient){
        PreparedStatement ps;

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(createBankClientQuery);
            ps.setInt(1, bankClient.getBankId());
            ps.setString(2, bankClient.getFirstName());
            ps.setString(3, bankClient.getLastName());
            ps.setString(4, bankClient.getEmail());
            ps.setInt(5, bankClient.getSSN());
            ps.setString(6, bankClient.getUsername());
            ps.setString(7, bankClient.getPassword());

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
     * Removes a bank client given their id
     * @param bankClientId
     */
    public void removeBankClient(int bankClientId){
        PreparedStatement ps;

        try (Connection conn = ConnectionUtil.getConnection())
        {
            ps = conn.prepareStatement(removeBankClientQuery);
            ps.setInt(1, bankClientId);

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
