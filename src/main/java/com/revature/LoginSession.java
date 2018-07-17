package com.revature;

import com.revature.beans.BankClient;
import com.revature.dao.BankClientDAO;
import com.revature.util.SHA512Hash;

/**
 * Maintains login state of user
 */
public class LoginSession
{
    private BankClient loggedInClient;


    /**
     * Logs out user
     */
    public void logout()
    {
        loggedInClient = null;
    }

    /**
     * Logs in user
     *
     * @param username
     * @param password
     * @return true if successful, false otherwise
     */
    public boolean login(String username, String password)
    {
        BankClientDAO bankClientDAO = new BankClientDAO();

        BankClient pendingClient = bankClientDAO.getBankClientByUsername(username);

        if (pendingClient != null && password.equals(SHA512Hash.getSHA512SecurePassword(pendingClient.getPassword())))
        {
            loggedInClient = pendingClient;
            return true;
        }
        return false;
    }

    public BankClient getLoggedInClient()
    {
        return loggedInClient;
    }

    public boolean isLoggedIn()
    {
        return loggedInClient != null;
    }


}
