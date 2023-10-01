package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;

public class CloseAccountTask implements CloudTransactionTask {
    public static final CloseAccountTask COMMAND(Cloud cloud, Account account){
        return new CloseAccountTask(cloud, account);
    }
    private Cloud cloud;
    private Account account;
    private CloseAccountTask(Cloud cloud, Account account){
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
