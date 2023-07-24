package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;

public class CloseAccount implements CloudTransactionTask {
    public static final CloseAccount COMMAND(Cloud cloud, Account account){
        return new CloseAccount(cloud, account);
    }
    private Cloud cloud;
    private Account account;
    private CloseAccount(Cloud cloud, Account account){
        this.cloud = cloud;
        this.account = account;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public Account getAccount() {
        return account;
    }

}
