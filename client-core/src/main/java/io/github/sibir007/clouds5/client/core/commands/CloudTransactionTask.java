package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.core.commands.*;

public interface CloudTransactionTask {
    static AddCloud ADD_CLOUD(Cloud cloud){return AddCloud.COMMAND(cloud);}
    static NewAccount NEW_ACCOUNT(Cloud cloud, Account account){return NewAccount.COMMAND(cloud, account);}
    static CloseAccount CLOSE_ACCOUNT(Cloud cloud, Account account){return CloseAccount.COMMAND(cloud, account);}
    static AuthoriseAccount AUTHORISE_ACCOUNT(Cloud cloud, Account account){return AuthoriseAccount.COMMAND(cloud, account);}
    static EditAccount EDIT_ACCOUNT(Cloud cloud, Account oldAccount, Account newAccount){return EditAccount.COMMAND(cloud, oldAccount, newAccount);}

}
