package ir.banobat.myapplication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class AccountUtils {

    public static final String ACCOUNT_TYPE = "ir.banobat";
    public static final String AUTH_TOKEN_TYPE = "ir.banobat";

    public static IServerAuthenticator mServerAuthenticator = new MyServerAuthenticator();

    public static Account getAccount(Context context, String accountName) {
        try{
            AccountManager accountManager = AccountManager.get(context);
            Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
            for (Account account : accounts) {
                if (account.name.equalsIgnoreCase(accountName)) {
                    return account;
                }
            }
            return null;
        }catch (SecurityException ex){
            return null;
        }
    }

}
