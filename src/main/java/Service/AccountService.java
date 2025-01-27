package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    //Constructor for an AccountService when a AccountDAO is provided.
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    //if username is not blank, password > 4, and username does not already exist, register account
    public Account registerAccount(Account account){
        if(account.getUsername() != "" && account.getPassword().length() > 4){
            if(accountDAO.getAccountByUsername(account.getUsername())==null){
                return accountDAO.registerAccount(account);
            }
        }
        return null;
        
    }

    public Account loginAccount(Account account){
        //if username already exists
        if(account.equals(accountDAO.getAccountByUsername(account.getUsername())))
            { return accountDAO.loginAccount(account); }
        else
            { return null; }
    }
}
