package com.revature;

import com.revature.beans.BankClient;

/**
 * Maintains login state of user
 */
public class LoginSession
{
    private BankClient loggedInClient;
    private boolean isLoggedIn;

    public BankClient getLoggedInClient()
    {
        return loggedInClient;
    }

    public void setLoggedInClient(BankClient loggedInClient)
    {
        this.loggedInClient = loggedInClient;
    }

    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn)
    {
        isLoggedIn = loggedIn;
    }
}
