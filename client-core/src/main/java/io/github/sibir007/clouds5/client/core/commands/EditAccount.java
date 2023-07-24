package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;

public class EditAccount implements CloudTransactionTask {
    public static final EditAccount COMMAND(Cloud cloud, Account oldAccount, Account newAccount){
        return new EditAccount(cloud, oldAccount, newAccount);
    }
    private Cloud cloud;
    private Account oldAccount;
    private Account newAccount;
    private EditAccount(Cloud cloud, Account oldAccount, Account newAccount){
        this.cloud = cloud;
        this.oldAccount = oldAccount;
        this.newAccount = newAccount;
    }

    public Cloud getCloud() {
        return cloud;
    }
    public Account getOldAccount() {
        return oldAccount;
    }
    public Account getNewAccount() {
        return newAccount;
    }

}
