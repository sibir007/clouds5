package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;

public class EditAccountTask implements CloudTransactionTask {
    public static final EditAccountTask COMMAND(Cloud cloud, Account oldAccount, Account newAccount){
        return new EditAccountTask(cloud, oldAccount, newAccount);
    }
    private Cloud cloud;
    private Account oldAccount;
    private Account newAccount;
    private EditAccountTask(Cloud cloud, Account oldAccount, Account newAccount){
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

    @Override
    public CommandType commandType() {
        return null;
    }
}
