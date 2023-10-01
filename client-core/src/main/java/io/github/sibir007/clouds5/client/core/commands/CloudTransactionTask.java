package io.github.sibir007.clouds5.client.core.commands;

import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;

public interface CloudTransactionTask {
    CommandType commandType();
    static AddCloudTask ADD_CLOUD(Cloud cloud){return AddCloudTask.COMMAND(cloud);}
    static NewAccountTask NEW_ACCOUNT(Cloud cloud, Account account){return NewAccountTask.COMMAND(cloud, account);}
    static CloseAccountTask CLOSE_ACCOUNT(Cloud cloud, Account account){return CloseAccountTask.COMMAND(cloud, account);}
    static AuthoriseAccountTask AUTHORISE_ACCOUNT(Cloud cloud, Account account){return AuthoriseAccountTask.COMMAND(cloud, account);}
    static EditAccountTask EDIT_ACCOUNT(Cloud cloud, Account oldAccount, Account newAccount){return EditAccountTask.COMMAND(cloud, oldAccount, newAccount);}

}
