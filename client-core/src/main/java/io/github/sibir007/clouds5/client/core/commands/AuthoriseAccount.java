package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;

public class AuthoriseAccount implements CloudTransactionTask {

    public static final AuthoriseAccount COMMAND(Cloud cloud, Account account){
        return new AuthoriseAccount(cloud, account);
    }
    private Cloud cloud;
    private Account account;
    private AuthoriseAccount(Cloud cloud, Account account){
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
