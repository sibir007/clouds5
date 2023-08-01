package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;

public class NewAccount implements CloudTransactionTask {
    public static final NewAccount COMMAND(Cloud cloud, Account account){
        return new NewAccount(cloud, account);
    }
    private Cloud cloud;
    private Account account;
    private NewAccount(Cloud cloud, Account account){
        this.cloud = cloud;
        this.account = account;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public CommandType commandType() {
        return null;
    }
}
